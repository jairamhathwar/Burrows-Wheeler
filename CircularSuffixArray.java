import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {
    private int n; // length of string
    private int[] indices; // indices array

    private class CircularSuffix implements Comparable<CircularSuffix> {
        private String string; // underlying string
        private int pivot; // pivot for circular array

        // comparable variant of string
        public CircularSuffix(String str, int piv) {
            string = str;
            pivot = piv;
        }

        // implements pivots with charAt
        private char character(int piv) {
            return string.charAt((piv + pivot) % n);
        }

        // compare two circular suffixes
        public int compareTo(CircularSuffix second) {
            for (int i = 0; i < n; i++) {
                if (this.character(i) < second.character(i)) {
                    return -1;
                }
                if (this.character(i) > second.character(i)) {
                    return 1;
                }
            }
            return 0;
        }
    }

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        n = s.length();
        CircularSuffix[] circularSuffixes = new CircularSuffix[n];
        for (int i = 0; i < n; i++) {
            circularSuffixes[i] = new CircularSuffix(s, i);
        }
        Arrays.sort(circularSuffixes);
        indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = circularSuffixes[i].pivot;
        }
    }

    // length of s
    public int length() {
        return n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("i is outside range");
        }
        return indices[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String string = "ABRACADABRA!";
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(string);
        StdOut.println("Length: " + circularSuffixArray.length());
        for (int i = 0; i < circularSuffixArray.length(); i++) {
            StdOut.println(circularSuffixArray.index(i));
        }
    }
}
