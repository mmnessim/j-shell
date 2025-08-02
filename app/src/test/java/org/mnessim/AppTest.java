package org.mnessim;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void testParse() {
        String command = "cat";
        ArgParser p = new ArgParser(command);
        assertEquals("cat", p.command, "Command should be 'cat'");
        assertEquals(null, p.flags, "Flags should be null for 'cat'");
        assertEquals(null, p.arguments, "Arguments should be null for 'cat'");

        String commandAndFlag = "cat -afde";
        ArgParser p2 = new ArgParser(commandAndFlag);
        assertEquals("cat", p2.command, "Command should be 'cat' for 'cat -afde'");
        assertArrayEquals(new char[] {'a', 'f', 'd', 'e'}, p2.flags, "Flags should be ['a','f','d','e'] for 'cat -afde'");
        assertArrayEquals(null, p2.arguments, "Arguments should be null for 'cat -afde'");

        String commandFlagAndArg = "cat -blorg src/main";
        ArgParser p3 = new ArgParser(commandFlagAndArg);
        assertEquals("cat", p3.command, "Command should be 'cat' for 'cat -blorg src/main'");
        assertArrayEquals(new char[] {'b', 'l', 'o', 'r', 'g'}, p3.flags, "Flags should be ['b','l','o','r','g'] for 'cat -blorg src/main'");
        assertArrayEquals(new String[] {"src/main"}, p3.arguments, "Arguments should be ['src/main'] for 'cat -blorg src/main'");

    }

}
