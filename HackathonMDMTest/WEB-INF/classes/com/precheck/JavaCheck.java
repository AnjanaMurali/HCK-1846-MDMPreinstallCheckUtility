package com.precheck;


public class JavaCheck {
    public static boolean checkJavaInstallation(String requiredJavaVersion) {
        String currentVersion = getJavaVersion();
        
        System.out.println("Current Java version: " + currentVersion);
   	 if(currentVersion.startsWith(requiredJavaVersion)==true) {
        	System.out.println("Java version check passed.");
        return true;
        }
        else {	
        	System.out.println("Java version check failed.");
        	return false;
        }
        
    }

    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }
}
