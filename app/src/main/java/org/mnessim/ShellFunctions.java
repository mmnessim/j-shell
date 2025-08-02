package org.mnessim;

import java.nio.file.Files;
import java.nio.file.Path;

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
        if (p.arguments == null) {
            return "";
        }
        try {
            String content = Files.readString(Path.of(p.arguments[0]));
            if (p.arguments.length >= 1) {
                /**
                 * Handle multiple arguments by concatenating to content variable
                 */
                for (int i = 1; i < p.arguments.length; i++) {
                    content = content.concat(Files.readString(Path.of(p.arguments[i])));
                }
            }
            return content;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
