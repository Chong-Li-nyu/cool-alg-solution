package grammar;

import java.io.*;
import java.util.Scanner;

public class InputOutput {
    String consoleReadLine() {

        java.util.Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    String consoleReadOneLine() throws IOException {
        /* Reader needs to take an input stream.

         * BufferedReader(Reader inputReader)
         * InputStreamReader(InputStream inputStream)
Here, inputReader is the stream that is linked to the instance of BufferedReader that is being created.
Reader is an abstract class. One of its concrete subclasses is InputStreamReader, which converts bytes to characters.
To obtain an InputStreamReader object that is linked to System.in, use the following constructor:
 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    /* Note that the Reader.read() method behaves very similary to that of InputStream: it returns the next character as
     an int, which we must cast to a char ourselves. This is because when the end of the stream is reached,
     -1 is returned just as with a InputStream.read().
     */
    void readByChar() throws IOException {
        InputStream in = new FileInputStream("charfile.txt");
        Reader r = new InputStreamReader(in, "US-ASCII");
        int intch;
        while ((intch = r.read()) != -1) {
            char ch = (char) intch;
            // ...
        }
    }
    public static void main(String[] args) throws IOException {
        InputOutput io = new InputOutput();
        String line = io.consoleReadOneLine();
        System.out.println(line);
    }
}
