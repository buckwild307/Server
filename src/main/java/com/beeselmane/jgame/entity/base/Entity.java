package com.beeselmane.jgame.entity.base;

import com.beeselmane.jgame.entity.effect.Effect;
import com.beeselmane.jgame.exception.EntityNotInsideRegistryException;
import com.beeselmane.jgame.registry.EntityRegistry;
import com.beeselmane.jgame.util.Direction;
import com.beeselmane.jgame.util.Vector;
import com.beeselmane.jgame.util.Velocity;
import com.beeselmane.jgame.world.World;

public abstract class Entity {
    protected static EntityBehavior behavior;
    protected boolean isFalling, isOnGround;
    protected double health = -1D, maxHealth = -1D, sinceFall;
    protected double pitch, yaw, roll, head_yaw, head_pitch, head_roll;
    protected PathFinder pathFinder;
    protected Vector vector;
    protected Effect effect;
    protected World world;
    private String name;
    private long id;

    public Entity(World world, EntityRegistry registry) {
        EntityRegistry.EntityRegistrySet registrySet = registry.new EntityRegistrySet();
        registrySet.getFrom(registry, this.getClass());
        if (registrySet.name() == null) throw new EntityNotInsideRegistryException();

        this.world = world;
        this.id = world.getOpenEntityID();
        this.vector = world.spawn(this);
    }

    public abstract void jump();

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public double getHealth() {
        return this.health;
    }

    public double sinceFall() {
        return this.sinceFall;
    }

    public boolean isOnGround() {
        return this.isOnGround;
    }

    public boolean isFalling() {
        return this.isFalling;
    }

    public double getPitch() {
        return this.pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public double getYaw() {
        return this.yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public double getRoll() {
        return this.roll;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }

    public double getHead_pitch() {
        return this.head_pitch;
    }

    public void setHead_pitch(double head_pitch) {
        this.head_pitch = head_pitch;
    }

    public double getHead_yaw() {
        return this.head_yaw;
    }

    public void setHead_yaw(double head_yaw) {
        this.head_yaw = head_yaw;
    }

    public double getHead_roll() {
        return this.head_roll;
    }

    public void setHead_roll(double head_roll) {
        this.head_roll = head_roll;
    }

    public long getID() {
        return this.id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public Vector getVector() {
        return this.vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    public void kill() {
        this.health = 0;
        this.world.tickEntity(this);
    }

    public void dealDamage(double damage) {
        this.health -= damage;
        if (this.health <= 0) this.kill();
    }

    public abstract void tick();

    protected static enum EntityBehavior {
        FRIENDLY,
        NUETRAL,
        HOSTILE,
        PLAYER,
        NONE
    }

    protected static abstract class PathFinder {
        public Entity entity;
        public int path_length = 0;
        public Motion[] path = new Motion[path_length];

        public PathFinder(Entity entity) {
            this.entity = entity;
        }

        public abstract void generate_path(int ticks);

        public void flush() {
            for (int i = 0; i < path_length; i++) {
                if (path[i] == Motion.STAND_STILL) {
                    entity.tick();
                } else if (path[i] == Motion.MOVE) {
                    entity.vector = new Vector(entity.vector.getPosition(), path[i].velocity);
                    entity.tick();
                } else if (path[i] == Motion.JUMP) {
                    entity.vector = new Vector(entity.vector.getPosition(), new Velocity(Direction.UP, 1.25));
                    entity.tick();
                }
            }
        }

        public enum Motion {
            STAND_STILL,
            MOVE,
            JUMP;

            public Velocity velocity;
        }
    }
}