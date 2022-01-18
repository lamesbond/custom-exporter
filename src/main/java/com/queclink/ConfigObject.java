package com.queclink;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConfigObject {
    private List<String> port;
    private List<String> pathNames;
    private List<String> servicePorts;
}

