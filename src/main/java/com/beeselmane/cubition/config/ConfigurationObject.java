package com.beeselmane.cubition.config;

import java.util.List;
import java.util.Map;

public class ConfigurationObject {

    public static final String PATH_SEPARATOR = ".";
    public static final String BLANK_CONFIG = "\n";

    public Map<String, ConfigurationTypeEnum> type = null;
    public Map<String, Object> value = null;
    public List<String> key = null;

    ConfigurationObject(Map<String, ConfigurationTypeEnum> type, Map<String, Object> value, List<String> refkey) {
        this.value = value;
        this.key = refkey;
        this.type = type;
    }
}
