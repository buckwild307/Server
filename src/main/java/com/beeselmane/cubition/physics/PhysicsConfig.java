package com.beeselmane.cubition.physics;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class PhysicsConfig {

    private static PhysicsConfig instance;

    /**
     * Note: This file is a placeholder.
     */

    private static void load() throws FileNotFoundException {
        InputStream in = PhysicsConfig.class.getResourceAsStream("/physics.json");
        if (in == null) {
            throw new FileNotFoundException("/physics.json was not found in classpath!");
        }

        // Read it in
    }

    public static PhysicsConfig getInstance() {
        if (instance == null) {
            try {
                load();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        return instance;
    }
}
