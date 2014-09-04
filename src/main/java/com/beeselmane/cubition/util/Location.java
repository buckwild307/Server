package com.beeselmane.cubition.util;

import com.beeselmane.cubition.world.World;

public class Location {

    private double x, y, z;
    private World world;

    public Location(double x, double y, double z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public void x(double x) {
        this.x = x;
    }

    public void y(double y) {
        this.y = y;
    }

    public void z(double z) {
        this.z = z;
    }
}
