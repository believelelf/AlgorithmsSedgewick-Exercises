package com.weiquding.algorithmsSedgewick.sorting;

import java.io.File;

public class ListAllFiles {

    public static void listFile(File file){
        if(file == null || !file.exists()){
            return;
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File subFile : files){
                listFile(subFile);
            }
        }
        System.out.println(file.getPath());
    }

    public static void main(String[] args) {
        listFile(new File("E:\\gitrepo\\Document\\图灵架构师第二期"));
    }
}
