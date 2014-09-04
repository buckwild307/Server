package com.beeselmane.cubition.util;

public class Vector {
    Location location;
    Velocity momentum;

    public Vector(Location location, Velocity momentum) {
        this.momentum = momentum;
        this.location = location;
    }

    public Location getPosition() {
        return this.location;
    }

    public Velocity getMomentum() {
        return this.momentum;
    }

    public void setVelocity(Velocity velocity) {
        this.momentum = velocity;
    }

    public void moveTo(Location location) {
        this.location = location;
    }

    public void move() {
        Direction direction = this.momentum.getDirection();

        switch (direction) {
            case UP:
                this.location.y(this.location.y() + this.momentum.getSpeed());
                break;
            case DOWN:
                this.location.y(this.location.y() - this.momentum.getSpeed());
                break;
            case RIGHT:
                this.location.x(this.location.x() + this.momentum.getSpeed());
                break;
            case LEFT:
                this.location.x(this.location.x() - this.momentum.getSpeed());
                break;
            case FORWARD:
                this.location.z(this.location.z() + this.momentum.getSpeed());
                break;
            case BACKWARD:
                this.location.z(this.location.z() - this.momentum.getSpeed());
                break;
        }
    }
}

/*

    LEFT(1),
    RIGHT(2),
    FORWARD(3),
    BACKWARD(4),
    UP(5),
    DOWN(6),
    FORWARD_LEFT(7),
    FORWARD_RIGHT(8),
    BACKWARD_LEFT(9),
    BACKWARD_RIGHT(10),
    FORWARD_UP(11),
    FORWARD_DOWN(12),
    BACKWARD_UP(13),
    BACKWARD_DOWN(14),
    LEFT_UP(15),
    LEFT_DOWN(16),
    RIGHT_UP(17),
    RIGHT_DOWN(18);

 */
