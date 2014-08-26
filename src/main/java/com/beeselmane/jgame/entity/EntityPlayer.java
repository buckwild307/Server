package com.beeselmane.jgame.entity;

import com.beeselmane.jgame.entity.base.Entity;
import com.beeselmane.jgame.player.Player;
import com.beeselmane.jgame.registry.EntityRegistry;
import com.beeselmane.jgame.util.Direction;
import com.beeselmane.jgame.util.Vector;
import com.beeselmane.jgame.util.Velocity;
import com.beeselmane.jgame.world.World;

public class EntityPlayer extends Entity {
    private Player player;

    public EntityPlayer(World world, EntityRegistry registry, Player player) {
        super(world, registry);
        this.pathFinder = new PathFinder(this);
        this.player = player;
    }

    @Override
    public void tick() {
        this.getVector().move();
    }

    @Override
    public void jump() {
        this.setVector(new Vector(this.getVector().getPosition(), new Velocity(Direction.UP, this.getVector().getMomentum().getSpeed())));
    }

    private static final class PathFinder extends Entity.PathFinder {
        public PathFinder(EntityPlayer player) {
            super(player);
        }

        @Override
        public void generate_path(int ticks) {
            this.path_length = ticks;
            this.path = new Motion[path_length];
        }
    }

    static {
        EntityPlayer.behavior = EntityBehavior.PLAYER;
    }
}