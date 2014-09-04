package com.beeselmane.cubition.util;

public class Velocity {

    private Direction direction;
    private double speed;

    public Velocity(Direction direction, double speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
