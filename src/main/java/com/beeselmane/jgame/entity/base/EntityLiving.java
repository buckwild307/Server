package com.beeselmane.jgame.entity.base;

import com.beeselmane.jgame.registry.EntityRegistry;
import com.beeselmane.jgame.world.World;

public abstract class EntityLiving extends Entity implements Damagable {
    public EntityLiving(World world, EntityRegistry registry) {
        // TODO: Note: This constructor is a placeholder.
        super(world, registry);
    }
}
