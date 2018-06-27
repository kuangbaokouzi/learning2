package com.laowuandhisfriends.javaio.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class NioTest2 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("/home/kouzikaile/workspace/learning3/Java IO/Java NIO之Channel", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("/home/kouzikaile/workspace/learning3/Java IO/Java NIO之Channel-to", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
        toFile.close();
        fromChannel.close();
    }
}
