package com.zk.qpm.analysis;

import com.zk.qpm.QPMRAnalysisResult.SocTempInfo;
import com.zk.qpm.callback.IAnalysisCallback;

public class QPMSocTempAnalysis extends QPMBaseAnalysis {

    public void onCollectSocTempInfo(int cpuTemp, int gpuTemp) {
        analysisResult.socTempInfo.cpuTemp = cpuTemp;
        analysisResult.socTempInfo.gpuTemp = gpuTemp;
        callBackManager.refreshInfo(IAnalysisCallback.TYPE_REFRESH_SOC_TEMP_INFO, analysisResult);
    }

}
