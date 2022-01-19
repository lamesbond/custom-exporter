package com.queclink.tcpconnection;

import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.queclink.CustomExporter.listServicePorts;

public class TcpConnectionMetrics extends Collector {
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfsTcpConnection = new ArrayList<MetricFamilySamples>();

        // With no labels.
        mfsTcpConnection.add(new GaugeMetricFamily("tcp_connection", "help", 42));

        // With labels
        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("tcp_connection", "help", Arrays.asList("serviceport"));
        for (int i = 0; i < listServicePorts.length; i++) {
            long tcpConnection = TcpConnection.getTcpConnectionCount(listServicePorts[i]);
            labeledGauge.addMetric(Arrays.asList(listServicePorts[i]), tcpConnection);
        }

        mfsTcpConnection.add(labeledGauge);
        return mfsTcpConnection;
    }
}