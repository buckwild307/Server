package com.beeselmane.cubition.entity.base;

public interface Damagable {

    public double getHealth();

    public double getMaxHealth();

    public void dealDamage(double damage);

    public void kill();
}
