package org.mnessim;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class ShellFunctions {

    /**
     * Implementation of cat builtin function Displays the contents of a file to
     * the terminal output
     *
     * TODO: Implement flags to add functionality handle multiple arguments
     * maybe
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
                //contents = contents.concat("\n");
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

    /**
     * Display operating system and JVM info Arguments and flags not implemented
     * yet...
     */
    public static String getInfo() {
        var os = System.getProperty("os.name");
        var version = System.getProperty("os.version");
        var arch = System.getProperty("os.arch");

        String contents = os + " " + version + " " + arch;

        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long maxMemory = runtime.maxMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;

        contents = contents.concat("\nJVM Memory Usage: " + totalMemory + " MB\nFree: " + freeMemory + " MB\nUsed: " + usedMemory + " MB\nMax: " + maxMemory + " MB");

        //System.out.println("JVM Memory Usage:");
        //System.out.println("Total Memory (bytes): " + totalMemory);
        //System.out.println("Free Memory (bytes): " + freeMemory);
        //System.out.println("Used Memory (bytes): " + usedMemory);
        //System.out.println("Max Memory (bytes): " + maxMemory);

        return contents;
    }

    public static String LS(ArgParser p) {
        String contents = "";
        String pathString;
        if (p.arguments == null) {
            pathString = ".";
        } else {
            pathString = p.getArguments()[0];
        }

        Path dir = Paths.get(pathString);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    contents = contents.concat("\u001B[32m  " + entry.getFileName().toString() + "\u001B[33m\n");
                    continue;
                }
                contents = contents.concat(entry.getFileName().toString() + "\n");
            }
            return contents;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String help() {
        String helpMessage = """
        Welcome to J-Shell: A Java based Shell emulator
        Commands:
            help -> display this help message
            sysinfo -> display OS info and JVM memory usage
            cat -> display file contents
                [-n] include line numbers
            exit -> exit the terminal

        TODO: These commands are not yet implemented:
            ls -> display contents of a directory
            touch -> create a new file
            >> -> write output of command to a file
            rm -> remove file or directory
            | -> pipe output of command into input of another
            cd -> change directory
            pwd -> print working directory

        Written by Mounir Nessim
        mmnessim@gmail.com https://github.com/mmnessim
        """;

        return helpMessage;
    }

}
