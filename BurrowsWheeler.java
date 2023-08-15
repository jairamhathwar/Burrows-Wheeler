import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 256; // alphabet size

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String string = BinaryStdIn.readString();
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(string);
        int n = string.length();
        for (int i = 0; i < n; i++) {
            if (circularSuffixArray.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            int index = (circularSuffixArray.index(i) - 1 + n) % n;
            BinaryStdOut.write(string.charAt(index));
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        // transformed string from StdIn
        int first = BinaryStdIn.readInt();
        String string = BinaryStdIn.readString();
        char[] chars = string.toCharArray();

        // variables required for key-indexed counting
        int n = string.length();
        int[] count = new int[R + 1];
        int[] next = new int[n];
        char[] firstLetter = new char[n];

        // key-indexed counting to inverse transform
        for (int i = 0; i < n; i++)
            count[chars[i] + 1]++;
        for (int i = 0; i < R; i++)
            count[i + 1] += count[i];
        for (int i = 0; i < n; i++) {
            int ind = count[chars[i]]++;
            firstLetter[ind] = chars[i];
            next[ind] = i;
        }
        for (int i = 0; i < n; i++) {
            BinaryStdOut.write(firstLetter[first]);
            first = next[first];
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-"))
            transform();
        else if (args[0].equals("+"))
            inverseTransform();
    }
}
