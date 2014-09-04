package com.beeselmane.cubition.mod;

import com.beeselmane.cubition.exception.InvalidModException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JavaModLoader {
    private boolean loaded = false;
    private String modFile;
    private JavaMod mod;

    public JavaModLoader(String modFile) {
        this.modFile = modFile;
    }

    public JavaMod getMod() {
        return this.mod;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public JavaMod load() {
        JavaModInfo modInfo = new JavaModInfo(this.modFile);
        Class<? extends JavaMod> modMain = modInfo.getMainAsClass();

        try {
            Constructor modConstructor = modMain.getDeclaredConstructor(JavaModLoader.class, JavaModInfo.class);
            JavaMod mod = (JavaMod) modConstructor.newInstance(this, modInfo);
            this.loaded = true;
            this.mod = mod;
            return mod;
        } catch (NoSuchMethodException ex) {
            throw new InvalidModException("Mod does not have correct constructor arguments: " + modInfo.getName());
        } catch (IllegalAccessException ex) {
            throw new InvalidModException("Mod does not have correct constructor access: " + modInfo.getName());
        } catch (InstantiationException ex) {
            throw new InvalidModException("Mod main could not be constructed: " + modInfo.getName());
        } catch (InvocationTargetException ex) {
            throw new InvalidModException("Mod constructor uses wrong argument types: " + modInfo.getName());
        }
    }

    public void enableHandledMod() {
        if (this.loaded) {
            this.mod.enable();
        }
    }
}
