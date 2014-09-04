package com.beeselmane.cubition.mod;

/**
 * Base class for JVM-based modifications to Cubition. This class should be
 * extended by the main class of all mods / plugins for Cubition.
 */
public abstract class JavaMod {

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

    public final void enable() {
        if (!this.enabled) {
            this.onEnable();
        }
    }

    public void onEnable() {
    }

    public void onShutdown() {
    }
}
