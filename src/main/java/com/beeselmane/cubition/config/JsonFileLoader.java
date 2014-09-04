package com.beeselmane.cubition.config;

import com.beeselmane.cubition.util.FileUtil;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonFileLoader {

    private ConfigurationObject fileObject = null;
    private String filePath = null;
    private File file = null;

    public JsonFileLoader(File file, Map<String, ConfigurationTypeEnum> configMap) {
        this.filePath = file.getPath();
        this.file = file;

        this.load(configMap);
    }

    public JsonFileLoader(String filename, Map<String, ConfigurationTypeEnum> configMap) {
        this.filePath = filename;
        this.file = new File(filename);

        this.load(configMap);
    }

    private Object[] readArray(JsonReader reader, ConfigurationTypeEnum[] types, String currentkey, Map<String,
            ConfigurationTypeEnum> map) throws IOException {
        Object[] array = new Object[types.length];
        int i = 0;
        reader.beginArray();

        while (reader.hasNext()) {
            switch (types[i]) {
                case TYPE_OBJECT:
                    array[i] = this.readObject(currentkey + ConfigurationObject.PATH_SEPARATOR + "[", reader, map);
                    break;
                case TYPE_ARRAY:
                    array[i] = this.readArray(reader, types, currentkey + ConfigurationObject.PATH_SEPARATOR + "[",
                            map);
                    break;
                case TYPE_STRING:
                    array[i] = reader.nextString();
                    break;
                case TYPE_DOUBLE:
                    array[i] = reader.nextDouble();
                    break;
                case TYPE_LONG:
                    array[i] = reader.nextLong();
                case TYPE_INTEGER:
                    array[i] = reader.nextInt();
                    break;
                case TYPE_BYTE:
                    array[i] = (byte) reader.nextInt();
                    break;
                case TYPE_BOOLEAN:
                    array[i] = reader.nextBoolean();
                    break;
                case TYPE_NULL:
                    reader.nextNull();
                    array[i] = null;
                    break;
                default:
                    reader.skipValue();
            }

            i++;
        }

        reader.endArray();
        return array;
    }

    private ConfigurationObject readObject(String key, JsonReader reader, Map<String,
            ConfigurationTypeEnum> map) throws IOException {
        ConfigurationObject object = new ConfigurationObject(new HashMap<String, ConfigurationTypeEnum>(),
                new HashMap<String, Object>(), new ArrayList<String>());
        reader.beginObject();

        while (reader.hasNext()) {
            String localkey = reader.nextName();
            String globalkey = key + ConfigurationObject.PATH_SEPARATOR + localkey;

            if (map.containsKey(globalkey)) {
                object.key.add(globalkey);
                object.type.put(globalkey, map.get(globalkey));

                switch (map.get(globalkey)) {
                    case TYPE_OBJECT:
                        object.value.put(globalkey, this.readObject(globalkey, reader, map));
                        break;
                    case TYPE_ARRAY:
                        object.value.put(globalkey, this.readArray(reader, map.get(globalkey).arrayTypes, globalkey,
                                map));
                        break;
                    case TYPE_STRING:
                        object.value.put(globalkey, reader.nextString());
                        break;
                    case TYPE_LONG:
                        object.value.put(globalkey, reader.nextLong());
                        break;
                    case TYPE_INTEGER:
                        object.value.put(globalkey, reader.nextInt());
                        break;
                    case TYPE_BYTE:
                        object.value.put(globalkey, (byte) reader.nextInt());
                        break;
                    case TYPE_BOOLEAN:
                        object.value.put(globalkey, reader.nextBoolean());
                        break;
                    case TYPE_NULL:
                        reader.nextNull();
                        object.value.put(globalkey, null);
                        break;
                    default:
                        reader.skipValue();
                }
            } else {
                reader.skipValue();
            }
        }

        reader.endObject();
        return object;
    }

    private void load(Map<String, ConfigurationTypeEnum> map) {
        try {
            String fileContents = FileUtil.readFull(this.filePath);
            if (fileContents.equals(ConfigurationObject.BLANK_CONFIG)) {
                return;
            }
            JsonReader reader = new JsonReader(new StringReader(fileContents));
            this.fileObject = this.readObject("", reader, map);
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    private void writeArray(Object[] array, JsonWriter writer) throws IOException {
        writer.beginArray();

        for (Object object : array) {
            if (object == null) {
                writer.nullValue();
            } else if (object instanceof ConfigurationObject) {
                this.writeObject((ConfigurationObject) object, writer);
            } else if (object instanceof Object[]) {
                this.writeArray((Object[]) object, writer);
            } else if (object instanceof String) {
                writer.value((String) object);
            } else if (object instanceof Long || object instanceof Double || object instanceof Integer || object
                    instanceof Byte) {
                writer.value((double) object);
            } else if (object instanceof Boolean) {
                writer.value((boolean) object);
            } else {
                writer.nullValue();
            }
        }

        writer.endArray();
    }

    private void writeObject(ConfigurationObject object, JsonWriter writer) throws IOException {
        writer.beginObject();

        for (String lkey : object.key) {
            writer.value(lkey);

            switch (object.type.get(lkey)) {
                case TYPE_OBJECT:
                    this.writeObject((ConfigurationObject) object.value.get(lkey), writer);
                    break;
                case TYPE_ARRAY:
                    this.writeArray((Object[]) object.value.get(lkey), writer);
                    break;
                case TYPE_STRING:
                    writer.value((String) object.value.get(lkey));
                    break;
                case TYPE_LONG:
                case TYPE_DOUBLE:
                case TYPE_INTEGER:
                case TYPE_BYTE:
                    writer.value((double) object.value.get(lkey));
                    break;
                case TYPE_BOOLEAN:
                    writer.value((boolean) object.value.get(lkey));
                    break;
                case TYPE_NULL:
                    writer.nullValue();
                    break;
                default:
                    writer.nullValue();
            }
        }

        writer.endObject();
    }

    public void save() {
        try {
            JsonWriter writer = new JsonWriter(new FileWriter(this.file));
            this.writeObject(this.fileObject, writer);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public ConfigurationObject getObject() {
        return this.fileObject;
    }
}
