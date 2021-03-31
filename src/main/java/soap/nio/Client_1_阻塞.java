package soap.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by ZhangPY on 2021/3/31
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain: Client_1_阻塞
 */
@Slf4j
public class Client_1_阻塞 {

    /** 阻塞模式客户端 **/

    public static void main(String[] args) throws IOException {
        /** 创建SocketChannel **/
        SocketChannel socketChannel = SocketChannel.open();
        /** 建立链接 **/
        socketChannel.connect(new InetSocketAddress("localhost" , 8080));

        log.debug("waiting... ...");
    }
}
