package com.precheck;

import java.io.IOException;

public class MDMPreInstallUtility {

    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length != 1) {
            System.out.println("Usage: java MDMPreInstallUtility <config-file-path>");
            return;
        }

       String configFilePath = args[0];//or we can
      //  String configFilePath = "config.properties";

        try {
            ConfigReader configReader = new ConfigReader(configFilePath);

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
            
            boolean diskSpaceCheck = DiskSpaceCheck.checkDiskSpace(requiredDiskSpace);
            boolean memoryCheck = MemoryCheck.checkMemory(requiredMemory);
            boolean javaCheck = JavaCheck.checkJavaInstallation(requiredJavaVersion);
            boolean osCheck = OSCheck.checkOS(requiredOs);
            boolean directoryAccessCheck = DirectoryAccessCheck.checkDirectories(checkDirectories);
            boolean envVarCheck = EnvVarCheck.checkEnvVars(requiredEnvVars);
            
            String dbCheck = DatabaseCheck.checkDatabaseConnection(dbUrl, dbUser, dbPassword);

            if (diskSpaceCheck && memoryCheck && javaCheck && osCheck && directoryAccessCheck && envVarCheck && dbCheck.equals("Database connection successful.")) {
                System.out.println("Pre-installation checks passed. Environment is compatible.");
            } else {
                System.out.println("Pre-installation checks failed. Please address the issues above.");
            }   

        } catch (IOException e) {
            System.out.println("Error reading configuration file: " + e.getMessage());
        }
    }
}
