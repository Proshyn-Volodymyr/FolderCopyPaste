package com.gmail.vladimirprocean;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        File newFile;
        File sourceFolder = new File("D:\\Education\\Grammarly");
        File destinationFolder = new File("D:\\Education\\newFolder");
        destinationFolder.mkdirs();
        File[] files = sourceFolder.listFiles();
        for (File file:files
             ) {
            newFile = new File(destinationFolder.getPath() + "\\" + file.getName());
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Provider provider = new Provider(file, data);
            Consumer consumer = new Consumer(newFile, data);
            Thread threadOne = new Thread(provider);
            Thread threadTwo = new Thread(consumer);
            threadOne.start();
            threadTwo.start();
        }

    }
}