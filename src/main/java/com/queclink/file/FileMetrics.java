package com.queclink.file;

import com.queclink.ConfigObject;
import com.queclink.ReadConfig;
import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileMetrics extends Collector {
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfsFileMetrics = new ArrayList<MetricFamilySamples>();

        String metricName = "filelist";
        // With no labels.
        mfsFileMetrics.add(new GaugeMetricFamily("fileldist" ,"help", 42));
        ConfigObject configs = ReadConfig.getConfigs();

        String[] fileStrs = {"pathname","filename"};
        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("filelist", "help", Arrays.asList(fileStrs));
        String[] pathNames = configs.getPathNames().toArray(new String[0]);
        for (int oi = 0; oi < pathNames.length; oi++) {
            File[] fileList = FileOrder.getFileOrder(pathNames[oi]);
            for (Integer ii = 0; ii < fileList.length; ii++) {
                String stri = ii.toString();
                String[] strs = {pathNames[oi],fileList[ii].getName()};
                List<String> strsToList1 = Arrays.asList(strs);
                Long filesize = FileSize.getFileSize(pathNames[oi]+"\\"+fileList[ii].getName());
                labeledGauge.addMetric(strsToList1, filesize);
            }
        }

        //labeledGauge.addMetric(Arrays.asList("bar"), 5);
        mfsFileMetrics.add(labeledGauge);

        return mfsFileMetrics;
    }
}