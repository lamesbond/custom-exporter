package com.queclink.filelist;

import com.queclink.utils.JudgeSystem;
import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.queclink.CustomExporter.pathNames;

public class FileListMetrics extends Collector {
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfsFileMetrics = new ArrayList<MetricFamilySamples>();

        // With no labels.
        mfsFileMetrics.add(new GaugeMetricFamily("fileldist" ,"help", 42));

        String[] fileStrs = {"pathname","filename"};
        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("filelist", "help", Arrays.asList(fileStrs));

        String pathConnection = "";
        if (JudgeSystem.isLinux()) {
            pathConnection = "//";
        } else if (JudgeSystem.isWindows()) {
            pathConnection = "\\";
        } else {
            System.out.println("不是linux，也不是windows系统");
        }
        for (int oi = 0; oi < pathNames.length; oi++) {
            File[] fileList = FileOrder.getFileOrder(pathNames[oi]);
            for (Integer ii = 0; ii < fileList.length && ii < 20; ii++) {
                List<String> metricLabels = Arrays.asList(pathNames[oi],fileList[ii].getName());
                Long metricValue = FileSize.getFileSize(pathNames[oi]+pathConnection+fileList[ii].getName());
                labeledGauge.addMetric(metricLabels, metricValue);
            }
        }
        mfsFileMetrics.add(labeledGauge);
        return mfsFileMetrics;
    }
}