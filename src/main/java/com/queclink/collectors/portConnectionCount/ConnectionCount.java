package com.queclink.collectors.portConnectionCount;

import com.queclink.utils.JudgeSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConnectionCount {
    public static long getPortConnectionCount(String port) {
        long connectionCount = 0;
        try {
            Runtime runtime = Runtime.getRuntime();
            String[] cmd = null;
            if (JudgeSystem.isLinux()) {
                cmd = new String[]{"/bin/sh", "-c", "netstat -anp|grep " + port + " | wc -l"};
            } else if (JudgeSystem.isWindows()) {
                cmd = new String[]{"cmd", "/C", "netstat -ano|find " + '"' + port + '"' + " /c"};
            } else {
                System.out.println("不是linux，也不是windows系统");
            }
            Process proc = runtime.exec(cmd);
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            line = br.readLine();
            connectionCount = Long.parseLong(line);

            //直到读完为止
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connectionCount;
    }
}


