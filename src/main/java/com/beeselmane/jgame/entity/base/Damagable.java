package com.beeselmane.jgame.entity.base;

public interface Damagable {
    public double getHealth();

    public double getMaxHealth();

    public void dealDamage(double damage);

    public void kill();
}
