package com.precheck;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/config")
public class ConfigServlet extends HttpServlet {
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
        if ("10.5".equals(userInput) && "windows".equalsIgnoreCase(userOS)) {
            properties.setProperty("requiredDiskSpace", "15");
            properties.setProperty("requiredMemory", "0");
            properties.setProperty("requiredJavaVersion", "1.8");
            properties.setProperty("requiredCpuCores", "4");
            properties.setProperty("requiredOs", "windows");
            properties.setProperty("checkDirectories", "C:\\Program Files\\Informatica,C:\\Users\\Administrator\\siperian,D:\\EAP-7.4.0");
            properties.setProperty("requiredEnvVars", "JAVA_HOME,INFA_HOME");
            properties.setProperty("dbUrl", "jdbc:sqlserver://localhost:1433;databaseName=master");
            properties.setProperty("dbUser", "sa");
            properties.setProperty("dbPassword", "Mssql123");
        } else if ("10.5".equals(userInput) && "linux".equalsIgnoreCase(userOS)) {
            properties.setProperty("requiredDiskSpace", "15");
            properties.setProperty("requiredMemory", "30");
            properties.setProperty("requiredJavaVersion", "1.8");
            properties.setProperty("requiredCpuCores", "4");
            properties.setProperty("requiredOs", "linux");
            properties.setProperty("checkDirectories", "/opt/informatica,/var/log/informatica");
            properties.setProperty("requiredEnvVars", "JAVA_HOME,INFA_HOME");
            properties.setProperty("dbUrl", "jdbc:sqlserver://localhost:1433;databaseName=master");
            properties.setProperty("dbUser", "sa");
            properties.setProperty("dbPassword", "Mssql123");
        } else if ("10.4".equals(userInput) && "windows".equalsIgnoreCase(userOS)) {
            properties.setProperty("requiredDiskSpace", "5");
            properties.setProperty("requiredMemory", "20");
            properties.setProperty("requiredJavaVersion", "1.8");
            properties.setProperty("requiredCpuCores", "4");
            properties.setProperty("requiredOs", "windows");
            properties.setProperty("checkDirectories", "C:\\Program Files\\Informatica,C:\\Users\\Administrator\\siperian,D:\\EAP-7.4.0");
            properties.setProperty("requiredEnvVars", "JAVA_HOME,INFA_HOME");
            properties.setProperty("dbUrl", "jdbc:sqlserver://localhost:1433;databaseName=master");
            properties.setProperty("dbUser", "sa");
            properties.setProperty("dbPassword", "Mssql123");
        } else if ("10.4".equals(userInput) && "linux".equalsIgnoreCase(userOS)) {
            properties.setProperty("requiredDiskSpace", "5");
            properties.setProperty("requiredMemory", "20");
            properties.setProperty("requiredJavaVersion", "1.8");
            properties.setProperty("requiredCpuCores", "4");
            properties.setProperty("requiredOs", "linux");
            properties.setProperty("checkDirectories", "/opt/informatica,/var/log/informatica");
            properties.setProperty("requiredEnvVars", "JAVA_HOME,INFA_HOME");
            properties.setProperty("dbUrl", "jdbc:sqlserver://localhost:1433;databaseName=master");
            properties.setProperty("dbUser", "sa");
            properties.setProperty("dbPassword", "Mssql123");
        }
   //System.out.println("GetServlet:"+getServletContext());
        // Write properties to config.properties file
        //String configFilePath = "C:\\Users\\Administrator\\Music\\HackathonMDMTest\\src\\main\\webapp\\config.properties";
        String configFilePath = getServletContext().getRealPath("/WEB-INF/config.properties");

        try (FileOutputStream outputStream = new FileOutputStream(configFilePath)) {
            properties.store(outputStream, "#Config properties for " + userInput + " on " + userOS);
        } catch (IOException e) {
            throw new ServletException("Error writing properties to file.", e);
        }

        try (FileOutputStream outputStream = new FileOutputStream(configFilePath)) {
            properties.store(outputStream, "#Config properties for " + userInput + " on " + userOS);
        } catch (IOException e) {
            throw new ServletException("Error writing properties to file.", e);
        }

        // Set the response content type and write HTML response with background image
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>MDM Pre-Installation Utility</title>");
        response.getWriter().println("<style>");
        response.getWriter().println("html, body { height: 100%; margin: 0; padding: 0; font-family: Arial, sans-serif; }");
        response.getWriter().println("body {");
        response.getWriter().println("background-image: url('images/infa-social.png');"); // Adjust path to your background image
        response.getWriter().println("background-size: cover;");
        response.getWriter().println("background-position: center;");
        response.getWriter().println("}");
        response.getWriter().println("h1 { color: white; text-align: center; padding-top: 50px; }");
        response.getWriter().println("</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>MDM Pre-Installation Utility</h1>");
        response.getWriter().println("<p style='text-align: center;'><a href='index.jsp'>Back to Home</a></p>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
