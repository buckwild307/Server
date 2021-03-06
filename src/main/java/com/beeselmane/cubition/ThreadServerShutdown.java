package com.beeselmane.cubition;

import java.io.File;

public class ThreadServerShutdown extends Thread {

    @Override
    public void run() {
        File tmpDir = new File(CubitionServer.getPath() + "mods/tmp");

        if (!tmpDir.delete()) {
            for (String file : tmpDir.list()) {
                if (!new File(file).delete()) {
                    System.err.println("Could not remove temporary file: " + file);
                }
            }
        }
    }
}
