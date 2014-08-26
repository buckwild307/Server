package com.beeselmane.jgame.mod;

public class JavaMod // Mod Main classes should extend this
{
    private JavaModLoader loader;
    private JavaModInfo info;
    private boolean enabled;

    public JavaMod(JavaModLoader loader, JavaModInfo info) {
        super();
        this.info = info;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public String getName() {
        return info.getName();
    }

    public void enable() {
        if (!this.enabled) {
            this.onEnable();
        }
    }

    public void onEnable() {
    }

    public void onShutdown() {
    }
}
