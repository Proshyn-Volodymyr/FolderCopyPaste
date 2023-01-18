package com.gmail.vladimirprocean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Consumer implements Runnable {
    private File path;
    private Data data;

    public Consumer(File path, Data data) {
        this.path = path;
        this.data = data;
    }

    @Override
    public void run() {
        byte[] buffer;
        synchronized (data) {
            while (data.getData() == null) {
                try {
                    data.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.notifyAll();
            }
        }
        buffer = data.getData();
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
