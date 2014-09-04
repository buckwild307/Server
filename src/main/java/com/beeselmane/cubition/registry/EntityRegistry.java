package com.beeselmane.cubition.registry;

import com.beeselmane.cubition.entity.base.Entity;
import com.beeselmane.cubition.exception.EntityAlreadyRegisteredException;

import java.util.HashMap;
import java.util.Map;

public class EntityRegistry {
    private Map<Class<? extends Entity>, Integer> entity_id = new HashMap<>();
    private Map<Class<? extends Entity>, String> entity_name = new HashMap<>();
    private Map<Integer, Class<? extends Entity>> id_entity = new HashMap<>();
    private Map<String, Class<? extends Entity>> name_entity = new HashMap<>();
    private Map<Integer, String> id_name = new HashMap<>();
    private Map<String, Integer> name_id = new HashMap<>();

    public void register(int id, String name, Class<? extends Entity> entity) {
        if (this.entity_id.containsKey(entity)) throw new EntityAlreadyRegisteredException(null, entity);
        if (this.id_entity.containsKey(id)) throw new EntityAlreadyRegisteredException(null, id);
        if (this.name_entity.containsKey(name)) throw new EntityAlreadyRegisteredException(null, name);

        this.entity_id.put(entity, id);
        this.entity_name.put(entity, name);
        this.id_entity.put(id, entity);
        this.name_entity.put(name, entity);
        this.id_name.put(id, name);
        this.name_id.put(name, id);
    }

    public void register(EntityRegistrySet set) {
        if (this.entity_id.containsKey(set.entity)) throw new EntityAlreadyRegisteredException(null, set.entity);
        if (this.id_entity.containsKey(set.id)) throw new EntityAlreadyRegisteredException(null, set.id);
        if (this.name_entity.containsKey(set.name)) throw new EntityAlreadyRegisteredException(null, set.name);

        this.entity_id.put(set.entity, set.id);
        this.entity_name.put(set.entity, set.name);
        this.id_entity.put(set.id, set.entity);
        this.name_entity.put(set.name, set.entity);
        this.id_name.put(set.id, set.name);
        this.name_id.put(set.name, set.id);
    }

    public void remove(int id) {
        if (!id_name.containsKey(id)) return;

        EntityRegistrySet set = new EntityRegistrySet();
        set.getFrom(this, id);

        this.entity_id.remove(set.entity);
        this.entity_name.remove(set.entity);
        this.id_entity.remove(set.id);
        this.name_entity.remove(set.name);
        this.id_name.remove(set.id);
        this.name_id.remove(set.name);
    }

    public void remove(String name) {
        if (!name_id.containsKey(name)) return;

        EntityRegistrySet set = new EntityRegistrySet();
        set.getFrom(this, name);

        this.entity_id.remove(set.entity);
        this.entity_name.remove(set.entity);
        this.id_entity.remove(set.id);
        this.name_entity.remove(set.name);
        this.id_name.remove(set.id);
        this.name_id.remove(set.name);
    }

    public void remove(Class<? extends Entity> entity) {
        if (!entity_id.containsKey(entity)) return;

        EntityRegistrySet set = new EntityRegistrySet();
        set.getFrom(this, entity);

        this.entity_id.remove(set.entity);
        this.entity_name.remove(set.entity);
        this.id_entity.remove(set.id);
        this.name_entity.remove(set.name);
        this.id_name.remove(set.id);
        this.name_id.remove(set.name);
    }

    public class EntityRegistrySet {
        private int id;
        private String name;
        private Class<? extends Entity> entity;

        public int id() {
            return this.id;
        }

        public String name() {
            return this.name;
        }

        public Class<? extends Entity> entity() {
            return this.entity;
        }

        public void getFrom(EntityRegistry registry, int id) {
            this.id = id;
            this.name = registry.id_name.get(id);
            this.entity = registry.id_entity.get(id);
        }

        public void getFrom(EntityRegistry registry, String name) {
            this.name = name;
            this.id = name_id.get(name);
            this.entity = registry.name_entity.get(name);
        }

        public void getFrom(EntityRegistry registry, Class<? extends Entity> entity) {
            this.entity = entity;
            this.id = entity_id.get(entity);
            this.name = registry.entity_name.get(entity);
        }
    }
}