package com.beeselmane.cubition.world;

import com.beeselmane.cubition.util.Vector;
import com.beeselmane.cubition.entity.base.Entity;

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
