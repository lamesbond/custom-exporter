package com.queclink;

import com.queclink.utils.ConfigObject;
import io.prometheus.client.exporter.HTTPServer;
import java.io.IOException;

public class CustomExporter {
    public static ConfigObject configObject = new ConfigObject();

    public static String[] fileSize_pathNames = null;
    public static String[] portConnectionCount_Ports = null;

    public static void main(String[] args) throws IOException {
        configObject = ConfigObject.getConfigObject();
        String[] listPort = configObject.getPort().toArray(new String[0]);
        fileSize_pathNames = configObject.getFileSize_pathNames().toArray(new String[0]);
        portConnectionCount_Ports = configObject.getPortConnectionCount_Ports().toArray(new String[0]);
        int intPort = Integer.parseInt(listPort[0]);

        new com.queclink.collectors.fileSize.Metric().register();
        new com.queclink.collectors.portConnectionCount.Metric().register();

        HTTPServer server = new HTTPServer(intPort);
    }
}

