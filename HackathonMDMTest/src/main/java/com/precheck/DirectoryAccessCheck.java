package com.precheck;

import java.io.File;

public class DirectoryAccessCheck {

    public static boolean checkDirectories(String[] directories) {
        boolean allChecksPassed = true;

        for (String dir : directories) {
            File directory = new File(dir);

          
                if (!dir.contains(dir.toLowerCase())) {
                
            if (!directory.exists()) {
                System.out.println("Directory does not exist: " + dir);
                allChecksPassed = false;
                continue;
            }

            if (!directory.isDirectory()) {
                System.out.println("Not a directory: " + dir);
                allChecksPassed = false;
                continue;
            }

            if (!directory.canRead()) {
                System.out.println("No read permission for directory: " + dir);
                allChecksPassed = false;
            }

            if (!directory.canWrite()) {
                System.out.println("No write permission for directory: " + dir);
                allChecksPassed = false;
            }
        }
                else {
               	 allChecksPassed = true;}
               }
            
           

        if (allChecksPassed) {
            System.out.println("Directory access check passed.");
        } else {
            System.out.println("Directory access check failed.");
        }

        return allChecksPassed;
    
    }
}
