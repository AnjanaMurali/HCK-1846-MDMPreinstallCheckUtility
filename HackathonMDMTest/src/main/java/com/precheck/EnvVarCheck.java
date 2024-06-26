package com.precheck;

public class EnvVarCheck {
    public static boolean checkEnvVars(String[] envVars) {
        for (String envVar : envVars) {
            String value = System.getenv(envVar);
            if (value == null || value.isEmpty()) {
                System.out.println("Environment variable check failed for: " + envVar);
                return false;
            }
        }
        System.out.println("Environment variable check passed.");
        return true;
    }
}
