package com.laowuandhisfriends.javaio.pipeline;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeDemo {
    public static void main(String[] args) throws Exception {
        final PipedOutputStream outputStream = new PipedOutputStream();
        final PipedInputStream inputStream = new PipedInputStream(outputStream);

        Thread t1 = new Thread(() -> {
            try {
                outputStream.write("Hello world, pipe!".getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                int data;
                while ((data = inputStream.read()) != -1) {
                    System.out.println((char) data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
