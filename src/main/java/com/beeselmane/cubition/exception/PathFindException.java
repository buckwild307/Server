package com.beeselmane.cubition.exception;

import java.io.PrintStream;

public class PathFindException extends Exception {

    private String message = null;
    private int exit = 1;

    public PathFindException(String message, int exit) {
        this.message = message;
        this.exit = exit;
    }

    @Override
    public void printStackTrace(PrintStream stream) {
        stream.println(message);
        System.exit(this.exit);
    }
}
