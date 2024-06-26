package com.precheck;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader(String filePath) throws IOException {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        }
    }

    public long getRequiredDiskSpace() {
        return Long.parseLong(properties.getProperty("requiredDiskSpace"));
    }

    public long getRequiredMemory() {
        return Long.parseLong(properties.getProperty("requiredMemory"));
    }

    public String getRequiredJavaVersion() {
        return properties.getProperty("requiredJavaVersion");
    }

    public int getRequiredCpuCores() {
        return Integer.parseInt(properties.getProperty("requiredCpuCores"));
    }

    public String getRequiredOs() {
        return properties.getProperty("requiredOs");
    }

    public String[] getCheckDirectories() {
        return properties.getProperty("checkDirectories").split(",");
    }

    public String[] getRequiredEnvVars() {
        return properties.getProperty("requiredEnvVars").split(",");
    }

   public String getDbUser() {
        return properties.getProperty("dbUser");
    }

    public String getDbPassword() {
        return properties.getProperty("dbPassword");
    }

	public String getDbUrl() {
		return properties.getProperty("dbUrl");
	}
}
