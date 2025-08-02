package org.mnessim;

import java.util.Arrays;

class ArgParser {
    String command;
    char[] flags;
    String[] arguments;


    public ArgParser(String input) {
        try {
            parse(input);
        } catch (ParseException e) {
            System.err.println("Could not parse: "+ e.getMessage());
        }
    }

    /**
     * Splits input into words and parses into commands, flags, and arguments
     * Called by ArgParser constructor
     *
     * @param input user input
     */
    private void parse(String input) throws ParseException {
        String[] parts = input.split(" ");
        System.out.println(parts[0]);
        switch (parts.length) {
            case 0 -> {
                throw new ParseException("No input given");
            }
            case 1 -> this.command = parts[0];
            case 2 -> {
                this.command = parts[0];
                if (isFlag(parts[1])) {
                    parseFlags(parts[1]);
                } else {
                    this.arguments = Arrays.copyOfRange(parts, 1, parts.length);
                }
            }
            default -> {
                this.command = parts[0];
                if (isFlag(parts[1])) {
                    parseFlags(parts[1]);
                    this.arguments = Arrays.copyOfRange(parts, 2, parts.length);
                } else {
                    this.arguments = Arrays.copyOfRange(parts, 1, parts.length);
                }
            }
        }
    }

    private void parseFlags(String input) {
        this.flags = input.substring(1).toCharArray();
    }

    private boolean isFlag(String input) {
        return input.startsWith("-");
    }

    public String getCommand() {
        return this.command;
    }

    public char[] getFlags() {
        return this.flags;
    }

    public String[] getArguments() {
        return this.arguments;
    }
}

class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }
}
