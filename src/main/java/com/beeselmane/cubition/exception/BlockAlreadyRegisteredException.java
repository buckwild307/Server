package com.beeselmane.cubition.exception;

import com.beeselmane.cubition.world.Block;

import java.io.PrintStream;

public class BlockAlreadyRegisteredException extends RuntimeException {

    private Class<? extends Block> block;
    private String name;
    private int id;

    public BlockAlreadyRegisteredException(String message, int id) {
        super(message);

        this.id = id;
        this.block = null;
        this.name = null;
    }

    public BlockAlreadyRegisteredException(String message, String name) {
        super(message);

        this.name = name;
        this.block = null;
        this.id = -1;
    }

    public BlockAlreadyRegisteredException(String message, Class<? extends Block> block) {
        super(message);

        this.block = block;
        this.name = null;
        this.id = -1;
    }

    @Override
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(PrintStream stream) {
        if (this.getMessage() != null) {
            stream.println(this.getMessage());
        }
        stream.println("BlockAlreadyRegisteredException: " + ((name != null) ? name : (this.block != null) ? block :
                id) + " already registered!");
        stream.println("Caused by: ");
        Thread.dumpStack();
    }
}
