package com.queclink.tcpconnection;

import com.queclink.ReadConfig;
import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TcpConnection extends Collector {
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfsTcpConnection = new ArrayList<MetricFamilySamples>();

        // With no labels.
        mfsTcpConnection.add(new GaugeMetricFamily("tcp_connection", "help", 42));

        // With labels
        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("tcp_connection", "help", Arrays.asList("serviceport"));
        String[] listServicePorts = ReadConfig.getConfigs().getServicePorts().toArray(new String[0]);
        for (int i = 0; i < listServicePorts.length; i++) {
            long tcpConnection = GetTcpConnection.getTcpConnection(listServicePorts[i]);
            labeledGauge.addMetric(Arrays.asList(listServicePorts[i]), tcpConnection);
        }

//        labeledGauge.addMetric(Arrays.asList("bar"), line);
        mfsTcpConnection.add(labeledGauge);

        return mfsTcpConnection;
    }
}