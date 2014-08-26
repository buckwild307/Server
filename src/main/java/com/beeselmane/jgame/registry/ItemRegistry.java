package com.beeselmane.jgame.registry;

import com.beeselmane.jgame.exception.ItemAlreadyRegisteredException;
import com.beeselmane.jgame.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private Map<Class<? extends ItemStack>, Integer> item_id = new HashMap<>();
    private Map<Class<? extends ItemStack>, String> item_name = new HashMap<>();
    private Map<Integer, Class<? extends ItemStack>> id_item = new HashMap<>();
    private Map<String, Class<? extends ItemStack>> name_item = new HashMap<>();
    private Map<Integer, String> id_name = new HashMap<>();
    private Map<String, Integer> name_id = new HashMap<>();

    public void register(int id, String name, Class<? extends ItemStack> item) {
        if (this.item_id.containsKey(item)) throw new ItemAlreadyRegisteredException(null, item);
        if (this.id_item.containsKey(id)) throw new ItemAlreadyRegisteredException(null, id);
        if (this.name_item.containsKey(name)) throw new ItemAlreadyRegisteredException(null, name);

        this.item_id.put(item, id);
        this.item_name.put(item, name);
        this.id_item.put(id, item);
        this.name_item.put(name, item);
        this.id_name.put(id, name);
        this.name_id.put(name, id);
    }

    public void register(ItemRegistrySet set) {
        if (this.item_id.containsKey(set.item)) throw new ItemAlreadyRegisteredException(null, set.item);
        if (this.id_item.containsKey(set.id)) throw new ItemAlreadyRegisteredException(null, set.id);
        if (this.name_item.containsKey(set.name)) throw new ItemAlreadyRegisteredException(null, set.name);

        this.item_id.put(set.item, set.id);
        this.item_name.put(set.item, set.name);
        this.id_item.put(set.id, set.item);
        this.name_item.put(set.name, set.item);
        this.id_name.put(set.id, set.name);
        this.name_id.put(set.name, set.id);
    }

    public void remove(int id) {
        if (!id_name.containsKey(id)) return;

        ItemRegistrySet set = new ItemRegistrySet();
        set.getFrom(this, id);

        this.item_id.remove(set.item);
        this.item_name.remove(set.item);
        this.id_item.remove(set.id);
        this.name_item.remove(set.name);
        this.id_name.remove(set.id);
        this.name_id.remove(set.name);
    }

    public void remove(String name) {
        if (!name_id.containsKey(name)) return;

        ItemRegistrySet set = new ItemRegistrySet();
        set.getFrom(this, name);

        this.item_id.remove(set.item);
        this.item_name.remove(set.item);
        this.id_item.remove(set.id);
        this.name_item.remove(set.name);
        this.id_name.remove(set.id);
        this.name_id.remove(set.name);
    }

    public void remove(Class<? extends ItemStack> item) {
        if (!item_id.containsKey(item)) return;

        ItemRegistrySet set = new ItemRegistrySet();
        set.getFrom(this, item);

        this.item_id.remove(set.item);
        this.item_name.remove(set.item);
        this.id_item.remove(set.id);
        this.name_item.remove(set.name);
        this.id_name.remove(set.id);
        this.name_id.remove(set.name);
    }

    public class ItemRegistrySet {
        private int id;
        private String name;
        private Class<? extends ItemStack> item;

        public int id() {
            return this.id;
        }

        public String name() {
            return this.name;
        }

        public Class<? extends ItemStack> item() {
            return this.item;
        }

        public void getFrom(ItemRegistry registry, int id) {
            this.id = id;
            this.name = registry.id_name.get(id);
            this.item = registry.id_item.get(id);
        }

        public void getFrom(ItemRegistry registry, String name) {
            this.name = name;
            this.id = registry.name_id.get(name);
            this.item = registry.name_item.get(name);
        }

        public void getFrom(ItemRegistry registry, Class<? extends ItemStack> item) {
            this.item = item;
            this.id = registry.item_id.get(item);
            this.name = registry.item_name.get(item);
        }
    }
}