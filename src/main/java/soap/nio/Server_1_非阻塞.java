package soap.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static soap.level_1.ByteBufferUtil.debugRead;

/**
 * Created by ZhangPY on 2021/3/31
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain: Server阻塞
 */
@Slf4j
public class Server_1_非阻塞 {

    /** 使用NIO 理解阻塞模式 - 单线程 **/


    public static void main(String[] args) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(16);

        /** 创建服务器 **/
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        /** 定义非阻塞 **/
        serverSocketChannel.configureBlocking(false);

        /** 绑定端口 **/
        serverSocketChannel.bind(new InetSocketAddress(8080));

        /** 建立链接的集合 **/
        List<SocketChannel> list = new ArrayList<>();
        while (true) {

            log.debug("正在链接中... ... ");

            /** 建立与客户端的链接 **/
            SocketChannel channel = serverSocketChannel.accept();

            if (channel != null) {
                channel.configureBlocking(false);

                log.debug("成功链接... {}" , channel);

                list.add(channel);
            }



            /** 循环集合 **/
            for (SocketChannel socketChannel : list) {

                log.debug("准备接受数据... {}" , socketChannel);
                /** 接受客户端的数据 **/
                int read = socketChannel.read(buffer);

                if (read > 0) {
                    /** buffer切换读模式 **/
                    debugRead(buffer);
                    /** buffer切换写模式 **/
                    buffer.clear();
                    log.debug("已经接收完数据... {}" , socketChannel);
                }

            }


        }




    }
}
