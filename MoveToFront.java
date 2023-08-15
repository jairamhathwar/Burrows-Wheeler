import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256; // alphabet size

    // apply move-to-front encoding, reading from stdin and writing to stdout
    public static void encode() {
        // establish array order of characters
        char[] charIndex = new char[R];
        for (int i = 0; i < R; i++) {
            charIndex[i] = (char) i;
        }

        // move each char encoding to the front
        while (!BinaryStdIn.isEmpty()) {
            // read each 8-bit character c
            char c = BinaryStdIn.readChar();
            char i = 0;
            char temp1 = charIndex[0];
            while (charIndex[i] != c) {
                char temp2 = charIndex[i];
                charIndex[i] = temp1;
                temp1 = temp2;
                i++;
            }
            charIndex[i] = temp1;
            charIndex[0] = c;
            BinaryStdOut.write(i);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from stdin and writing to stdout
    public static void decode() {
        // establish array order of characters
        char[] charIndex = new char[R];
        for (int i = 0; i < R; i++) {
            charIndex[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {
            // read each 8-bit character c
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(charIndex[c]);
            char tempChar = charIndex[c];
            while (c > 0) {
                charIndex[c] = charIndex[c - 1];
                c--;
            }
            charIndex[0] = tempChar;
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-"))
            encode();
        else if (args[0].equals("+"))
            decode();
    }
}
