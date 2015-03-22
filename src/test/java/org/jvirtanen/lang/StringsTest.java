package org.jvirtanen.lang;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringsTest {

    @Test
    public void decodeShort() {
        assertEquals("FO", Strings.decodeShort((short)0x464F));
    }

    @Test
    public void decodeInt() {
        assertEquals("FOOB", Strings.decodeInt(0x464F4F42));
    }

    @Test
    public void decodeLong() {
        assertEquals("FOOBAR  ", Strings.decodeLong(0x464F4F4241522020L));
    }

    @Test(expected=IllegalArgumentException.class)
    public void decodeWithInvalidCharacter() {
        Strings.decodeLong(0x464FD34241522020L);
    }

    @Test
    public void encodeShort() {
        assertEquals(0x464F, Strings.encodeShort("FO"));
    }

    @Test
    public void encodeInt() {
        assertEquals(0x464F4F42, Strings.encodeInt("FOOB"));
    }

    @Test
    public void encodeLong() {
        assertEquals(0x464F4F4241522020L, Strings.encodeLong("FOOBAR  "));
    }

    @Test
    public void encodeTooLong() {
        assertEquals(0x464F4F4241524241L, Strings.encodeLong("FOOBARBAZ"));
    }

    @Test
    public void encodeTooShort() {
        assertEquals(0x464F4F4241522020L, Strings.encodeLong("FOOBAR"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void encodeWithInvalidCharacter() {
        Strings.encodeLong("FÓOBARBA");
    }

}
