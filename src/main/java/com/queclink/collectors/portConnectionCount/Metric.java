package com.queclink.collectors.portConnectionCount;

import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.queclink.CustomExporter.portConnectionCount_Ports;

public class Metric extends Collector {
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfsPortConnectionCount = new ArrayList<MetricFamilySamples>();
        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("custom_tcp_connection", "统计端口连接数", Arrays.asList("serviceport"));
        for (int i = 0; i < portConnectionCount_Ports.length; i++) {
            long connectionCount = ConnectionCount.getPortConnectionCount(portConnectionCount_Ports[i]);
            labeledGauge.addMetric(Arrays.asList(portConnectionCount_Ports[i]), connectionCount);
        }

        mfsPortConnectionCount.add(labeledGauge);
        return mfsPortConnectionCount;
    }
}