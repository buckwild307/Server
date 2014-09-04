package com.beeselmane.cubition.mod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ServerModList {

    private Map<String, JavaMod> mods = new HashMap<>();
    private Map<String, JavaModLoader> loaders = new HashMap<>();

    public void register(JavaMod mod, JavaModLoader loader) {
        if (!this.mods.containsKey(mod.getName())) {
            this.mods.put(mod.getName(), mod);
            this.loaders.put(mod.getName(), loader);
        }
    }

    public JavaModLoader getLoader(String name) {
        return (this.loaders.containsKey(name)) ? this.loaders.get(name) : null;
    }

    public JavaMod getMod(String name) {
        return (this.mods.containsKey(name)) ? this.mods.get(name) : null;
    }

    public Set<String> nameSet() {
        return this.mods.keySet();
    }

    public long numMods() {
        return mods.size();
    }
}
