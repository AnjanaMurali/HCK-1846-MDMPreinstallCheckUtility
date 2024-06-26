package com.precheck;

import java.io.File;

public class DiskSpaceCheck {
    public static boolean checkDiskSpace(long requiredDiskSpace) {
        long freeSpace = getAvailableDiskSpace();
        System.out.println("Free disk space: " + freeSpace + " GB");
        if(freeSpace >= requiredDiskSpace) {
        	System.out.println("Disk Space check passed.");
        return true;
        }
        else {	
        	System.out.println("Disk Space check failed.");
        	return false;
        }
    }
        
    

    public static long getAvailableDiskSpace() {
        File root = new File("/");
        return root.getFreeSpace() / (1024 * 1024 * 1024); // convert bytes to GB
    }
}
