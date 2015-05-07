package org.jvirtanen.lang;

/**
 * This class contains methods for manipulating strings.
 */
public class Strings {

    private Strings() {
    }

    /**
     * Decode a two-character ASCII string from a short.
     *
     * @param s a short
     * @return a string
     * @throws IllegalArgumentException if the short does not contain an
     *   ASCII string
     */
    public static String decodeShort(short s) {
        return decode(s, 2);
    }

    /**
     * Decode a four-character ASCII string from an int.
     *
     * @param i an int
     * @return a string
     * @throws IllegalArgumentException if the int does not contain an ASCII
     *   string
     */
    public static String decodeInt(int i) {
        return decode(i, 4);
    }

    /**
     * Decode an eight-character ASCII string from a long.
     *
     * @param l a long
     * @return a string
     * @throws IllegalArgumentException if the long does not contain an
     *   ASCII string
     */
    public static String decodeLong(long l) {
        return decode(l, 8);
    }

    private static String decode(long l, int size) {
        StringBuilder b = new StringBuilder(size);

        for (int i = size - 1; i >= 0; i--) {
            char c = (char)((l >> (8 * i)) & 0xFF);
            if (c > 127)
                throw invalidCharacter(c);

            b.append(c);
        }

        return b.toString();
    }

    /**
     * Encode a two-character ASCII string to a short.
     *
     * <p>If the length of the string is less than two characters, it will
     * be left-justified and padded on the right with the space character.</p>
     *
     * <p>If the length of the string is more than two characters, only the
     * first two characters are encoded.</p>
     *
     * @param s a string
     * @return a short
     * @throws IllegalArgumentException if the string contains other than
     *   ASCII characters
     */
    public static short encodeShort(String s) {
        return (short)encode(s, 2);
    }

    /**
     * Encode a four-character ASCII string to an int.
     *
     * <p>If the length of the string is less than four characters, it will
     * be left-justified and padded on the right with the space character.</p>
     *
     * <p>If the length of the string is more than four characters, only the
     * first four characters are encoded.</p>
     *
     * @param s a string
     * @return an int
     * @throws IllegalArgumentException if the string contains other than
     *   ASCII characters
     */
    public static int encodeInt(String s) {
        return (int)encode(s, 4);
    }

    /**
     * Encode an eight-character ASCII string to a long.
     *
     * <p>If the length of the string is less than eight characters, it will
     * be left-justified and padded on the right with the space character.</p>
     *
     * <p>If the length of the string is more than eight characters, only the
     * first eight characters are encoded.</p>
     *
     * @param s a string
     * @return a long
     * @throws IllegalArgumentException if the string contains other than
     *   ASCII characters
     */
    public static long encodeLong(String s) {
        return encode(s, 8);
    }

    private static long encode(String s, int size) {
        long l = 0;
        int  i = 0;

        for (; i < Math.min(s.length(), size); i++) {
            char c = s.charAt(i);
            if (c > 127)
                throw invalidCharacter(c);

            l = (l << 8) | c;
        }

        for (; i < size; i++)
            l = (l << 8) | ' ';

        return l;
    }

    private static IllegalArgumentException invalidCharacter(int c) {
        return new IllegalArgumentException(String.format("Invalid character: \\u%04x", c));
    }

}
