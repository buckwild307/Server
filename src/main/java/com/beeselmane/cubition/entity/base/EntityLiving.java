package com.beeselmane.cubition.entity.base;

import com.beeselmane.cubition.registry.EntityRegistry;
import com.beeselmane.cubition.world.World;

public abstract class EntityLiving extends Entity implements Damagable {
    public EntityLiving(World world, EntityRegistry registry) {
        // TODO: Note: This constructor is a placeholder.
        super(world, registry);
    }
}
