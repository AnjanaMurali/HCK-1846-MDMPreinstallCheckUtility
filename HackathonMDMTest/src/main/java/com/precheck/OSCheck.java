package com.precheck;

public class OSCheck {
    public static boolean checkOS(String requiredOs) {
    	String osName = System.getProperty("os.name");
    	//String osVersion = System.getProperty("os.version");
    	System.out.println("Operating System: " + osName);
    	 String os = System.getProperty("os.name").toLowerCase();
         System.out.println("Current OS: " + os);
         if(os.contains(requiredOs.toLowerCase())){
        	 System.out.println("OS check passed");
         return true;
         }
         System.out.println("OS check passed");
         return false;
    }

    public static String getCurrentOS() {
        return System.getProperty("os.name");
    }
}



