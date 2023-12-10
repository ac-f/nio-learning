package channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelReadDemo1 {
    // FileChannel 讀取資料到 Buffer
    public static void main(String[] args) throws IOException {
        // 創建一個 FileChannel
        //   若要創建FileChannel，只能透過 InputStream, OutputStream, RandomAccessFile
        RandomAccessFile file = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel channel = file.getChannel();

        // 創建一個 Buffer
        final var byteBuffer = ByteBuffer.allocate(1024);

        // 將 FileChannel 的資料寫入 Buffer
        final int byteRead = channel.read(byteBuffer); // 而這個方法的回傳值，是讀取到的byte數量，如果是-1，代表已經讀取到檔案的末尾

        while(byteRead != -1) {
            // 將 Buffer 的position設置為0(初始位置)
            byteBuffer.flip();
            // 如果當前緩衝區還有剩餘的資料
            while (byteBuffer.hasRemaining()) {
                System.out.println((char) byteBuffer.get()); //這個get()方法，會讀取當前position的資料，並且將position往後移一位
            }
            // 清除buffer
            byteBuffer.clear();
        }
        file.close();
    }
}
