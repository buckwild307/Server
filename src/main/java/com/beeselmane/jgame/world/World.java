package com.beeselmane.jgame.world;

import com.beeselmane.jgame.entity.base.Entity;
import com.beeselmane.jgame.util.Vector;

public class World {
    private long assignedEntityIDs = -1;

    /**
     * Note: This file is a placeholder.
     */

    public void tickEntity(Entity entity) {
    }

    public long getOpenEntityID() {
        // Selby: assuming this is a incrementing ID.
        return assignedEntityIDs++;
    }

    public Vector spawn(Entity entity) {
        return null;
    }
}
