package com.queclink;

import com.queclink.file.FileMetrics;
import com.queclink.tcpconnection.TcpConnection;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;

public class CustomExporter {

    public static void main(String[] args) throws IOException {
        ConfigObject configs = ReadConfig.getConfigs();
        String[] listport = configs.getPort().toArray(new String[0]);
//        String processport = configss.getPort();
//        System.out.println(processport);
//        int porrt = Integer.parseInt(processport);
        new FileMetrics().register();
        new TcpConnection().register();
        String strport = listport[0];
        int intport = Integer.parseInt(strport);
        HTTPServer server = new HTTPServer(intport);
    }
}

