package com.precheck;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

//@WebServlet("/configAndCheck")
public class ConfigAndPreInstallCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user input from the form
        String userInput = request.getParameter("userInput");
        String userOS = request.getParameter("userOS");

        // Check if userInput or userOS are null and handle appropriately
        if (userInput == null || userOS == null) {
            throw new ServletException("User input or OS parameter is missing.");
        }

        // Populate properties based on user input
        Properties properties = new Properties();
        if (("10.5".equals(userInput) || "10.5.1".equals(userInput) || "10.5.2".equals(userInput)) && "windows".equalsIgnoreCase(userOS)) {
            properties.setProperty("requiredDiskSpace", "15");
            properties.setProperty("requiredMemory", "0");
            properties.setProperty("requiredJavaVersion", "21.0.3");
            properties.setProperty("requiredCpuCores", "4");
            properties.setProperty("requiredOs", "windows");
            properties.setProperty("checkDirectories", "C:\\Program Files\\Informatica,C:\\Users\\Administrator\\siperian,D:\\EAP-7.4.0");
            properties.setProperty("requiredEnvVars", "JAVA_HOME,INFA_HOME");
            properties.setProperty("dbUrl", "jdbc:sqlserver://localhost:1433;databaseName=master");
            properties.setProperty("dbUser", "sa");
            properties.setProperty("dbPassword", "Mssql123");
        } else if (("10.5".equals(userInput) || "10.5.1".equals(userInput) || "10.5.2".equals(userInput)) && "linux".equalsIgnoreCase(userOS)) {
            properties.setProperty("requiredDiskSpace", "15");
            properties.setProperty("requiredMemory", "0");
            properties.setProperty("requiredJavaVersion", "21.0.3");
            properties.setProperty("requiredCpuCores", "4");
            properties.setProperty("requiredOs", "linux");
            properties.setProperty("checkDirectories", "/opt/informatica,/var/log/informatica");
            properties.setProperty("requiredEnvVars", "JAVA_HOME,INFA_HOME");
            properties.setProperty("dbUrl", "jdbc:sqlserver://localhost:1433;databaseName=master");
            properties.setProperty("dbUser", "sa");
            properties.setProperty("dbPassword", "Mssql123");
        } else if (("10.4".equals(userInput) || "10.4.1".equals(userInput) || "10.4.2".equals(userInput) || "10.4.3".equals(userInput) || "10.4.4".equals(userInput) || "10.4.5".equals(userInput)) && "windows".equalsIgnoreCase(userOS)) {
            properties.setProperty("requiredDiskSpace", "15");
            properties.setProperty("requiredMemory", "30");
            properties.setProperty("requiredJavaVersion", "21.0.3");
            properties.setProperty("requiredCpuCores", "4");
            properties.setProperty("requiredOs", "windows");
            properties.setProperty("checkDirectories", "C:\\Program Files\\Informatica,C:\\Users\\Administrator\\siperian,D:\\EAP-7.4.0");
            properties.setProperty("requiredEnvVars", "JAVA_HOME,INFA_HOME");
            properties.setProperty("dbUrl", "jdbc:sqlserver://localhost:1433;databaseName=master");
            properties.setProperty("dbUser", "sa");
            properties.setProperty("dbPassword", "Mssql123");
        } else if (("10.4".equals(userInput) || "10.4.1".equals(userInput) || "10.4.2".equals(userInput) || "10.4.3".equals(userInput) || "10.4.4".equals(userInput) || "10.4.5".equals(userInput)) && "linux".equalsIgnoreCase(userOS)) {
            properties.setProperty("requiredDiskSpace", "15");
            properties.setProperty("requiredMemory", "30");
            properties.setProperty("requiredJavaVersion", "21.0.3");
            properties.setProperty("requiredCpuCores", "4");
            properties.setProperty("requiredOs", "linux");
            properties.setProperty("checkDirectories", "/opt/informatica,/var/log/informatica");
            properties.setProperty("requiredEnvVars", "JAVA_HOME,INFA_HOME");
            properties.setProperty("dbUrl", "jdbc:sqlserver://localhost:1433;databaseName=master");
            properties.setProperty("dbUser", "sa");
            properties.setProperty("dbPassword", "Mssql123");
        } else {
            throw new ServletException("Unsupported version or OS combination.");
        }

        // Write properties to config.properties file
        String configFilePath = getServletContext().getRealPath("/WEB-INF/config.properties");

        try (FileOutputStream outputStream = new FileOutputStream(configFilePath)) {
            properties.store(outputStream, "#Config properties for " + userInput + " on " + userOS);
        } catch (IOException e) {
            throw new ServletException("Error writing properties to file.", e);
        }

        // Perform pre-installation checks
        try {
            long requiredDiskSpace = Long.parseLong(properties.getProperty("requiredDiskSpace"));
            long requiredMemory = Long.parseLong(properties.getProperty("requiredMemory"));
            String requiredJavaVersion = properties.getProperty("requiredJavaVersion");
            int requiredCpuCores = Integer.parseInt(properties.getProperty("requiredCpuCores"));
            String requiredOs = properties.getProperty("requiredOs");
            String[] checkDirectories = properties.getProperty("checkDirectories").split(",");
            String[] requiredEnvVars = properties.getProperty("requiredEnvVars").split(",");
            String dbUrl = properties.getProperty("dbUrl");
            String dbUser = properties.getProperty("dbUser");
            String dbPassword = properties.getProperty("dbPassword");

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

            // Set attributes for results.jsp
            request.setAttribute("diskSpaceCheck", diskSpaceCheck);
            request.setAttribute("availableDiskSpace", availableDiskSpace);
            request.setAttribute("memoryCheck", memoryCheck);
            request.setAttribute("availableMemory", availableMemory);
            request.setAttribute("javaCheck", javaCheck);
            request.setAttribute("currentJavaVersion", currentJavaVersion);
            request.setAttribute("osCheck", osCheck);
            request.setAttribute("currentOS", requiredOs);
            request.setAttribute("directoryAccessCheck", directoryAccessCheck);
            request.setAttribute("envVarCheck", envVarCheck);
            request.setAttribute("dbCheck", dbCheckResult);

            boolean allChecksPassed = diskSpaceCheck && memoryCheck && javaCheck && osCheck && directoryAccessCheck && envVarCheck && dbCheckResult.equals("Database connection successful.");
            request.setAttribute("allChecksPassed", allChecksPassed);

            request.getRequestDispatcher("/results.jsp").forward(request, response);

        } catch (Exception e) {
        	e.printStackTrace();
//            LOGGER.log(Level.SEVERE, "Error reading configuration file: {0}", e.getMessage());
            request.setAttribute("error", "Version is not supported");
            request.getRequestDispatcher("/results.jsp").forward(request, response);
        }
    }
}
