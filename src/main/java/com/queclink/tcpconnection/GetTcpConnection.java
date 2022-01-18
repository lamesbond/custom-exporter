package com.queclink.tcpconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetTcpConnection {
    public static long getTcpConnection(String servicePort) {
        long tcpConnection = 0;
        try {
            Runtime runtime = Runtime.getRuntime();
//            String[] cmd = new String[]{"cmd", "/C", "netstat -ano|find " + '"' + servicePort + '"' + " /c"};
            String[] cmd = new String[]{"/bin/sh", "-c", "netstat -anp|grep " + servicePort + " | wc -l"};
            Process proc = runtime.exec(cmd);
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            line = br.readLine();
            tcpConnection = Long.parseLong(line);

            //直到读完为止
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tcpConnection;
    }
}


