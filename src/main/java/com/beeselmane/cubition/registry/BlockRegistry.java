package com.beeselmane.cubition.registry;

import com.beeselmane.cubition.exception.BlockAlreadyRegisteredException;
import com.beeselmane.cubition.world.Block;

import java.util.HashMap;
import java.util.Map;

public class BlockRegistry {

    private Map<Class<? extends Block>, Integer> block_id = new HashMap<>();
    private Map<Class<? extends Block>, String> block_name = new HashMap<>();
    private Map<Integer, Class<? extends Block>> id_block = new HashMap<>();
    private Map<String, Class<? extends Block>> name_block = new HashMap<>();
    private Map<Integer, String> id_name = new HashMap<>();
    private Map<String, Integer> name_id = new HashMap<>();

    public void register(int id, String name, Class<? extends Block> block) {
        if (this.block_id.containsKey(block)) {
            throw new BlockAlreadyRegisteredException(null, block);
        }
        if (this.id_block.containsKey(id)) {
            throw new BlockAlreadyRegisteredException(null, id);
        }
        if (this.name_block.containsKey(name)) {
            throw new BlockAlreadyRegisteredException(null, name);
        }

        this.block_id.put(block, id);
        this.block_name.put(block, name);
        this.id_block.put(id, block);
        this.name_block.put(name, block);
        this.id_name.put(id, name);
        this.name_id.put(name, id);
    }

    public void register(BlockRegistrySet set) {
        if (this.block_id.containsKey(set.block)) {
            throw new BlockAlreadyRegisteredException(null, set.block);
        }
        if (this.id_block.containsKey(set.id)) {
            throw new BlockAlreadyRegisteredException(null, set.id);
        }
        if (this.name_block.containsKey(set.name)) {
            throw new BlockAlreadyRegisteredException(null, set.name);
        }

        this.block_id.put(set.block, set.id);
        this.block_name.put(set.block, set.name);
        this.id_block.put(set.id, set.block);
        this.name_block.put(set.name, set.block);
        this.id_name.put(set.id, set.name);
        this.name_id.put(set.name, set.id);
    }

    public void remove(int id) {
        if (!id_name.containsKey(id)) {
            return;
        }

        BlockRegistrySet set = new BlockRegistrySet();
        set.getFrom(this, id);

        this.block_id.remove(set.block);
        this.block_name.remove(set.block);
        this.id_block.remove(set.id);
        this.name_block.remove(set.name);
        this.id_name.remove(set.id);
        this.name_id.remove(set.name);
    }

    public void remove(String name) {
        if (!name_id.containsKey(name)) {
            return;
        }

        BlockRegistrySet set = new BlockRegistrySet();
        set.getFrom(this, name);

        this.block_id.remove(set.block);
        this.block_name.remove(set.block);
        this.id_block.remove(set.id);
        this.name_block.remove(set.name);
        this.id_name.remove(set.id);
        this.name_id.remove(set.name);
    }

    public void remove(Class<? extends Block> block) {
        if (!block_id.containsKey(block)) {
            return;
        }

        BlockRegistrySet set = new BlockRegistrySet();
        set.getFrom(this, block);

        this.block_id.remove(set.block);
        this.block_name.remove(set.block);
        this.id_block.remove(set.id);
        this.name_block.remove(set.name);
        this.id_name.remove(set.id);
        this.name_id.remove(set.name);
    }

    public class BlockRegistrySet {

        private int id;
        private String name;
        private Class<? extends Block> block;

        public int id() {
            return this.id;
        }

        public String name() {
            return this.name;
        }

        public Class<? extends Block> block() {
            return this.block;
        }

        public void getFrom(BlockRegistry registry, int id) {
            this.id = id;
            this.name = registry.id_name.get(id);
            this.block = registry.id_block.get(id);
        }

        public void getFrom(BlockRegistry registry, String name) {
            this.name = name;
            this.id = name_id.get(name);
            this.block = registry.name_block.get(name);
        }

        public void getFrom(BlockRegistry registry, Class<? extends Block> block) {
            this.block = block;
            this.id = block_id.get(block);
            this.name = registry.block_name.get(block);
        }
    }
}