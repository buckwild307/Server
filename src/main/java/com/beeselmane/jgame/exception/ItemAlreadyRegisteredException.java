package com.beeselmane.jgame.exception;

import com.beeselmane.jgame.item.ItemStack;

import java.io.PrintStream;

public class ItemAlreadyRegisteredException extends RuntimeException {
    private int id;
    private String name;
    private Class<? extends ItemStack> item;

    public ItemAlreadyRegisteredException(String message, int id) {
        super(message);

        this.id = id;
        this.name = null;
        this.item = null;
    }

    public ItemAlreadyRegisteredException(String message, String name) {
        super(message);

        this.name = name;
        this.item = null;
        this.id = -1;
    }

    public ItemAlreadyRegisteredException(String message, Class<? extends ItemStack> item) {
        super(message);

        this.item = item;
        this.id = -1;
        this.name = null;
    }

    @Override
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(PrintStream stream) {
        if (this.getMessage() != null) stream.println(this.getMessage());
        stream.println("EntityAlreadyRegisteredException: " + ((name != null) ? name : (item != null) ? item : id) + " already registered!");
        stream.println("Caused by: ");
        Thread.dumpStack();
    }
}