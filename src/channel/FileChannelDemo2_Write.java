package channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class FileChannelDemo2_Write {
    // FileChannel 寫入資料到 文件
    public static void main(String[] args) throws IOException {
        // 創建一個 FileChannel
        RandomAccessFile file = new RandomAccessFile("data/nio-data.txt", "rw");
        final var channel = file.getChannel();

        // 創建一個 Buffer
        final var byteBuffer = ByteBuffer.allocate(1024);

        String newData = "Testing for FileChannel write() method";
        byteBuffer.clear();

        // 寫入內容
        byteBuffer.put(newData.getBytes());

        byteBuffer.flip();

        // 將 Buffer 的資料寫入 FileChannel
        while (byteBuffer.hasRemaining()) {
            try {
                channel.write(byteBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        channel.close();
        file.close();
    }
}
