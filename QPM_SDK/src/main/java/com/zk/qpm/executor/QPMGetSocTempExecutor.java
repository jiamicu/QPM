package com.zk.qpm.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.text.TextUtils;

import com.zk.qpm.QPMException;
import com.zk.qpm.analysis.QPMSocTempAnalysis;
import com.zk.qpm.floatview.QPMFloatViewType;
import com.zk.qpm.floatview.renderer.IFloatViewRenderer;
import com.zk.qpm.floatview.renderer.QPMGetSocTempInfoRenderer;
import com.zk.qpm.manager.QPMFloatViewManager;

public class QPMGetSocTempExecutor implements IExecutor {

    private QPMSocTempAnalysis analysis;
    private boolean isStop;

    public QPMGetSocTempExecutor() {
        analysis = new QPMSocTempAnalysis();
    }

    @Override
    public String type() {
        return QPMFloatViewType.TYPE_FLOAT_VIEW_SOC_TEMP_VIEW;
    }

    @Override
    public void createShowView(Context context) {
        IFloatViewRenderer socTempViewBean = new QPMGetSocTempInfoRenderer();
        QPMFloatViewManager.getInstance().addItem(socTempViewBean);
    }

    @Override
    public void destoryShowView() {
        QPMFloatViewManager.getInstance().removeItem(type());
    }

    @Override
    public void exec() throws QPMException {
        java.lang.Process process = null;
        try {
            process = Runtime.getRuntime().exec("cat /sys/class/thermal/thermal_zone0/temp /sys/class/thermal/thermal_zone1/temp");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            int count = 0;
            int cpuTemp =  0;
            int gpuTemp =  0;
            while (!isStop && (line = reader.readLine()) != null) {
                count++;
                line = line.trim();
                if (TextUtils.isEmpty(line)) {
                    continue;
                }
                if (count == 1) {
                    cpuTemp = Integer.parseInt(line);
                } else if (count == 2) {
                    gpuTemp = Integer.parseInt(line);
                }
            }
            analysis.onCollectSocTempInfo(cpuTemp, gpuTemp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    @Override
    public void stop() {
        isStop = true;
    }
}
