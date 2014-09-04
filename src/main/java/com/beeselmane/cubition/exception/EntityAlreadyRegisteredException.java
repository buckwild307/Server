package com.beeselmane.cubition.exception;

import com.beeselmane.cubition.entity.base.Entity;

import java.io.PrintStream;

public class EntityAlreadyRegisteredException extends RuntimeException {
    private int id;
    private String name;
    private Class<? extends Entity> entity;

    public EntityAlreadyRegisteredException(String message, int id) {
        super(message);

        this.id = id;
        this.name = null;
        this.entity = null;
    }

    public EntityAlreadyRegisteredException(String message, String name) {
        super(message);

        this.name = name;
        this.entity = null;
        this.id = -1;
    }

    public EntityAlreadyRegisteredException(String message, Class<? extends Entity> entity) {
        super(message);

        this.entity = entity;
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
        stream.println("EntityAlreadyRegisteredException: " + ((name != null) ? name : (entity != null) ? entity : id) + " already registered!");
        stream.println("Caused by: ");
        Thread.dumpStack();
    }
}
