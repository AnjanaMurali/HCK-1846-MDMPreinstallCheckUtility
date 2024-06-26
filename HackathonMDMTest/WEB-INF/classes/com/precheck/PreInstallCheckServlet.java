package com.precheck;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PreInstallCheckServlet
 */
public class PreInstallCheckServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 102831973239L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // String configFilePath = getServletContext().getRealPath("/WEB-INF/config.properties");
        //System.out.println("configFilePath:"+configFilePath);
        String cfgFilePath = getServletContext().getRealPath("/WEB-INF/config.properties");
        System.out.println("configFilePath:"+cfgFilePath);

        try {
            ConfigReader configReader = new ConfigReader(cfgFilePath);

            long requiredDiskSpace = configReader.getRequiredDiskSpace();
            long requiredMemory = configReader.getRequiredMemory();
            String requiredJavaVersion = configReader.getRequiredJavaVersion();
            int requiredCpuCores = configReader.getRequiredCpuCores();
            String requiredOs = configReader.getRequiredOs();
            String[] checkDirectories = configReader.getCheckDirectories();
            String[] requiredEnvVars = configReader.getRequiredEnvVars();
            String dbUrl = configReader.getDbUrl();
            String dbUser = configReader.getDbUser();
            String dbPassword = configReader.getDbPassword();

            long availableDiskSpace = DiskSpaceCheck.getAvailableDiskSpace();
            long availableMemory = MemoryCheck.getAvailableMemory();
            String currentJavaVersion = JavaCheck.getJavaVersion();
            boolean diskSpaceCheck = DiskSpaceCheck.checkDiskSpace(requiredDiskSpace);
            boolean memoryCheck = MemoryCheck.checkMemory(requiredMemory);
            boolean javaCheck = JavaCheck.checkJavaInstallation(requiredJavaVersion);
            boolean osCheck = OSCheck.checkOS(requiredOs);
            boolean directoryAccessCheck = DirectoryAccessCheck.checkDirectories(checkDirectories);
            boolean envVarCheck = EnvVarCheck.checkEnvVars(requiredEnvVars);
            String dbCheckResult = DatabaseCheck.checkDatabaseConnection(dbUrl, dbUser, dbPassword);

            // Set attributes
            request.setAttribute("diskSpaceCheck", diskSpaceCheck);
            request.setAttribute("availableDiskSpace", availableDiskSpace);
            request.setAttribute("memoryCheck", memoryCheck);
            request.setAttribute("availableMemory", availableMemory);
            request.setAttribute("javaCheck", javaCheck);
            request.setAttribute("currentJavaVersion", currentJavaVersion);
            request.setAttribute("osCheck", osCheck);
            request.setAttribute("currentOS", OSCheck.getCurrentOS());
            request.setAttribute("directoryAccessCheck", directoryAccessCheck);
            request.setAttribute("envVarCheck", envVarCheck);
            request.setAttribute("dbCheck", dbCheckResult);

            boolean allChecksPassed = diskSpaceCheck && memoryCheck && javaCheck && osCheck && directoryAccessCheck && envVarCheck && dbCheckResult.equals("Database connection successful.");
            request.setAttribute("allChecksPassed", allChecksPassed);

            request.getRequestDispatcher("/results.jsp").forward(request, response);

        } catch (Exception e) {
        	e.printStackTrace();
//            LOGGER.log(Level.SEVERE, "Error reading configuration file: {0}", e.getMessage());
            request.setAttribute("error", "Error reading configuration file, MDM from 10.3 versions is not supported");
            request.getRequestDispatcher("/results.jsp").forward(request, response);
        }
    }
}
