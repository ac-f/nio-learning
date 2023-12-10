package channel;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileChannelDemo3_TransferFromAndTo {
    // Channel 之間的資料傳輸 (把一個Channel的資料傳輸到另一個Channel = 複製檔案內容)
    public static void main(String[] args) throws IOException {
        // 創建兩個 FileChannel
        RandomAccessFile fromfile = new RandomAccessFile("data/nio-transfer-from.txt", "rw");
        final var fromChannel = fromfile.getChannel();

        RandomAccessFile tofile = new RandomAccessFile("data/nio-transfer-to.txt", "rw");
        final var toChannel = tofile.getChannel();

        long position = 0;
        long size = fromChannel.size();

        // 將 fromChannel 的資料傳輸到 toChannel
        toChannel.transferFrom(fromChannel, position, size);

        // 關閉
        fromChannel.close();
        toChannel.close();
        fromfile.close();
        tofile.close();
    }
}
