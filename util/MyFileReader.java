package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class MyFileReader {
    private String className;

    public MyFileReader(String className) { this.className = className; }

    public void read() {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader("src/dbfile/" + this.className + ".txt");
            br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null) {
                dealFileData(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public abstract void dealFileData(String line);
}
