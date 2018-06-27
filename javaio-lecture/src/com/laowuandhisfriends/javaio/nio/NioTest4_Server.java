package com.laowuandhisfriends.javaio.nio;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest4_Server {
    public static void main(String[] args) throws Exception {
        // 1.创建Selector对象
        Selector selector = Selector.open();
        // 2.打开一个服务器端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress(9999);
        serverSocketChannel.bind(isa);
        // 3.设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 4.为服务器通道注册连接就绪监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("==========welcome!!!==========");

        // 5.通过select方法选择一组键，其相应的通道已为 I/O操作准备就绪
        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    SocketChannel sc = serverSocketChannel.accept();
                    if (sc.isConnected()) {
                        System.out.println("连接到:"
                                + sc.socket().getInetAddress() + "--"
                                + sc.socket().getPort());
                    }
                    // 设置为非阻塞模式
                    sc.configureBlocking(false);
                    // 为返回的SocketChannel对象注册读就绪和写就绪监听
                    sc.register(selector, SelectionKey.OP_READ);
                }

                // 删除正在处理的key
                it.remove();
            }
        }
    }
}
