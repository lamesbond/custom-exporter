package com.queclink;

import com.queclink.filelist.FileListMetrics;
import com.queclink.tcpconnection.TcpConnectionMetrics;
import com.queclink.utils.ConfigObject;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;

public class CustomExporter {
    public static ConfigObject configObject = new ConfigObject();
    public static String[] pathNames = null;
    public static String[] listServicePorts = null;
    public static void main(String[] args) throws IOException {
        configObject = ConfigObject.getConfigObject();
        String[] listPort = configObject.getPort().toArray(new String[0]);
        pathNames = configObject.getPathNames().toArray(new String[0]);
        listServicePorts = configObject.getServicePorts().toArray(new String[0]);
        int intPort = Integer.parseInt(listPort[0]);

        new FileListMetrics().register();
        new TcpConnectionMetrics().register();

        HTTPServer server = new HTTPServer(intPort);
    }
}

