package org.mnessim;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;

class ShellFunctions {

    /**
     * Implementation of cat builtin function
     * Displays the contents of a file to the terminal output
     *
     * TODO:
     * Implement flags to add functionality
     * handle multiple arguments maybe
     *
     * @param p ArgParser class
     */
    public static String cat(ArgParser p) {
        String contents = "";
        if (p.arguments == null) {
            return contents;
        }

        try {
            boolean flag = p.getFlags() != null && new String(p.getFlags()).indexOf('n') != -1;
            for (String arg : p.getArguments()) {
                contents = getFileLines(arg, contents, flag);
                contents = contents.concat("\n");
            }

            //contents = getFileLines(p.arguments[0], contents, flag);
            return contents;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static String getFileLines(String path, String contents, boolean flag) throws IOException {
        int lineNo = 1;
        List<String> lines = Files.readAllLines(Path.of(path));
        if (flag) {
            for (String line : lines) {
                contents = contents.concat(lineNo + ": " + line + "\n");
                lineNo += 1;
            }
        } else {
            for (String line : lines) {
                contents = contents.concat(line + "\n");
            }   
        }
        return contents;
    }

}
