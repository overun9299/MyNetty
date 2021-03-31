package soap.level_1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by ZhangPY on 2021/3/29
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain: TestByteBuffer
 */
@Slf4j
public class TestByteBuffer {

    public static void main(String[] args) {

        /** FileChannel - 通过输入输出流 **/
        try (FileChannel channel = new FileInputStream(".\\src\\main\\java\\soap\\filedata\\TestByteBufferTxt.txt").getChannel()) {
            /** 准备缓冲区,分配空间 **/
            ByteBuffer allocate = ByteBuffer.allocate(10);

            while (true) {
                /** 将输入流写入缓冲区 **/
                int read = channel.read(allocate);
                /** 判断读取完成 **/
                if (read == -1) {
                    break;
                }
                /** 切换到读模式 **/
                allocate.flip();

                while (allocate.hasRemaining()) { /** 是否还有未读的数据 **/
                    byte byteGet = allocate.get();
                    System.out.println((char) byteGet);
                }
                /** 切换为写模式 **/
                allocate.clear();
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
