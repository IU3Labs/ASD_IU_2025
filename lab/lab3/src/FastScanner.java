import java.io.BufferedInputStream;
import java.io.IOException;

public class FastScanner {
    private final BufferedInputStream in = new BufferedInputStream(System.in);
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0, len = 0;

    private int readByte() throws IOException {
        if (ptr >= len) {
            len = in.read(buffer);
            ptr = 0;
            if (len <= 0) return -1;
        }
        return buffer[ptr++];
    }

    public int nextInt() throws IOException {
        int c;
        do {
            c = readByte();
            if (c == -1) return Integer.MIN_VALUE;
        } while (c <= ' ');

        int sign = 1;
        if (c == '-') {
            sign = -1;
            c = readByte();
        }

        int val = 0;
        while (c > ' ') {
            val = val * 10 + (c - '0');
            c = readByte();
        }
        return val * sign;
    }
}
