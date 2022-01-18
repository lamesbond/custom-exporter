package com.queclink;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ReadConfig {

    public static ConfigObject getConfigs() {
        InputStream resource = ReadConfig.class.getClassLoader().getResourceAsStream("config.yaml");
        if(Objects.nonNull(resource)) {
            Yaml yaml = new Yaml();
            YAMLMapper yamlMapper = new YAMLMapper();
            ConfigObject configs = null;
            try {
                configs = yamlMapper.readValue(resource, ConfigObject.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //com.liubusi.ConfigObject configs = yaml.loadAs(resource, com.liubusi.ConfigObject.class);
            return configs;
        } else {
            return null;
        }
    }
}
