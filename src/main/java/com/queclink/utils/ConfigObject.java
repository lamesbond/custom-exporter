package com.queclink.utils;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.*;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConfigObject {
    private List<String> port;
    private List<String> pathNames;
    private List<String> servicePorts;

    public static ConfigObject getConfigObject() {
        InputStream resource = ConfigObject.class.getClassLoader().getResourceAsStream("config.yaml");
        if(Objects.nonNull(resource)) {
            YAMLMapper yamlMapper = new YAMLMapper();
            ConfigObject configs = null;
            try {
                configs = yamlMapper.readValue(resource, ConfigObject.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return configs;
        } else {
            return null;
        }
    }
}


