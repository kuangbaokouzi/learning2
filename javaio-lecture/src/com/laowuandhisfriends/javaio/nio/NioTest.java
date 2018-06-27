package com.laowuandhisfriends.javaio.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/home/kouzikaile/workspace/learning3/Java IO/Java NIO之Channel", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);

        int bytesRead;
        while ((bytesRead = fileChannel.read(buffer)) != -1) {
            System.out.println("Read: " + bytesRead);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            buffer.clear();
        }
        randomAccessFile.close();
    }
}
