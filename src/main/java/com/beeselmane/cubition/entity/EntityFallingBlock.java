package com.beeselmane.cubition.entity;

import com.beeselmane.cubition.entity.base.Entity;
import com.beeselmane.cubition.registry.EntityRegistry;
import com.beeselmane.cubition.util.Direction;
import com.beeselmane.cubition.util.Velocity;
import com.beeselmane.cubition.world.Block;
import com.beeselmane.cubition.world.World;

public class EntityFallingBlock extends Entity {

    public Block block;

    public EntityFallingBlock(World world, EntityRegistry registry, long id, Block block) {
        super(world, registry);
        this.block = block;
    }

    @Override
    public void tick() {
        this.getVector().move();
    }

    @Override
    public void jump() {
        // A FallingBlock cannot jump
        this.getVector().setVelocity(new Velocity(this.getVector().getMomentum().getDirection(),
                this.getVector().getMomentum().getSpeed() * 1.8D));
    }

    private static final class PathFinder extends Entity.PathFinder {

        public PathFinder(EntityFallingBlock block) {
            super(block);
        }

        public void generate_path(int ticks) {
            this.path_length = ticks;
            this.path = new Motion[path_length];

            for (int i = 0; i < path.length; i++) {
                path[i] = Motion.STAND_STILL;
                path[i].velocity = new Velocity(Direction.DOWN, (i));
                // TODO: Implement PhysicsConfig
                //* PhysicsConfig.getInstance().get(PhysicsConfig.GFORCE_SPECIFIC + "FallingBlock") + (i * 0.075 *
                // PhysicsConfig.getInstance().get(PhysicsConfig.GFORCE_ADDRESS))));
            }
        }
    }

    static {
        behavior = EntityBehavior.NONE;
    }
}