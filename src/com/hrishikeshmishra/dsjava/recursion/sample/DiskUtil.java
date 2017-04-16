package com.hrishikeshmishra.dsjava.recursion.sample;

import java.io.File;

/**
 * Created by hrishikesh.mishra on 30/12/15.
 *
 * Calculate disk-space recursively.
 */
public class DiskUtil {

    private final static long ONE_GB = 1024 * 1024 * 1024;

    public static void main(String[] args) {
        DiskUtil diskUtil = new DiskUtil();
        long usage = diskUtil.getDiskUsage(new File("/Users/hrishikesh.mishra/hrishi/"));
        System.out.println("\n\n\nTotal : " + (usage / ONE_GB)  + " GB");


    }
    public long getDiskUsage(File root){
        long total = root.length();

        if(root.isDirectory()){
            for(String childName: root.list()){
                File child = new File(root, childName);
                total += getDiskUsage(child);
            }
        }

        System.out.println(total + "\t" + root);
        return total;
    }
}
