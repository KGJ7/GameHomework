import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class fileReaderClass {

    public String readRandomLineFromFile(String x){
        try {
            RandomAccessFile file = new RandomAccessFile(x , "r");
            long fileLength = file.length();
            long randomPosition = ThreadLocalRandom.current().nextLong(fileLength);
            file.seek(randomPosition);
            file.readLine();
            return file.readLine();
        } catch (Exception e) {
            return ("ERROR OCCURED");
        }
    }

}
