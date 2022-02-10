package com.queclink.collectors.fileSize;

import com.queclink.utils.JudgeSystem;
import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.queclink.CustomExporter.fileSize_pathNames;

public class Metric extends Collector {
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfsFileSize = new ArrayList<MetricFamilySamples>();
        String[] fileStrs = {"pathname","filename"};
        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("custom_file_size", "统计目录下的文件以及大小", Arrays.asList(fileStrs));

        String directorySeparator = "";
        if (JudgeSystem.isLinux()) {
            directorySeparator = "//";
            System.out.println("检测到linux系统");
        } else if (JudgeSystem.isWindows()) {
            directorySeparator = "\\";
            System.out.println("检测到windows系统");
        } else {
            System.out.println("不是linux，也不是windows系统");
        }
        for (int m = 0; m < fileSize_pathNames.length; m++) {
            File[] fileList = FileOrder.getFileOrder(fileSize_pathNames[m]);
            if (fileList == null || 0 == fileList.length) {
                continue;
            }
            for (Integer n = 0; n < fileList.length && n < 20; n++) {
                List<String> metricLabels = Arrays.asList(fileSize_pathNames[m],fileList[n].getName());
                Long metricValue = FileSize.getFileSize(fileSize_pathNames[m]+directorySeparator+fileList[n].getName());
                labeledGauge.addMetric(metricLabels, metricValue);
            }
        }
        mfsFileSize.add(labeledGauge);
        return mfsFileSize;
    }
}