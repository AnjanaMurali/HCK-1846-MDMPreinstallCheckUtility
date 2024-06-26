package com.precheck;
public class MemoryCheck {
    public static boolean checkMemory(long requiredMemory) {
    	 long availableMemory = getAvailableMemory();
    	// System.out.println(requiredMemory);
    	  System.out.println("Available memory: " + availableMemory + " MB");
    	 // System.out.println(requiredMemory);
          if(availableMemory >= requiredMemory) {
           System.out.println("Available memory check passed.");
           return availableMemory >= requiredMemory;
           }
           else {	
           System.out.println("Available memory check failed.");
           	return false;
           }
    }

    public static long getAvailableMemory() {
    	Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        return (totalMemory - freeMemory) / (1024 * 1024); // Convert to MB
    }
}


