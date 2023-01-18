package com.gmail.vladimirprocean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Provider implements Runnable{
    private File path;
    private Data data;

    public Provider(File path, Data data) {
        this.path = path;
        this.data = data;
    }

    @Override
    public void run() {
        byte[] buffer;
        try (FileInputStream fis = new FileInputStream(path)) {
            buffer = new byte[1024];
            fis.read(buffer);
        }catch(IOException e){
            buffer = null;
            e.printStackTrace();
        }
        synchronized (data){
            data.setData(buffer);
            data.notifyAll();
        }
    }
}
