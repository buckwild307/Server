package com.beeselmane.cubition.util;

public enum Direction {
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
    private int serialID = 0;

    private Direction(int serial) {
        this.serialID = serial;
    }

    public static int serialize(Direction from) {
        return from.serialID;
    }

    public static Direction deserialize(int from) {
        for (Direction direction : Direction.values()) {
            if (direction.serialID == from) {
                return direction;
            }
        }

        return null;
    }

    public int getSerialID() {
        return this.serialID;
    }
}
