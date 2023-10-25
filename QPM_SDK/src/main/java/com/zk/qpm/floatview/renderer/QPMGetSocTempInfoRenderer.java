package com.zk.qpm.floatview.renderer;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zk.qpm.QPMRAnalysisResult;
import com.zk.qpm.R;
import com.zk.qpm.floatview.QPMFloatViewType;
import com.zk.qpm.manager.QPMManager;
import com.zk.qpm.manager.QPMRAnalysisManager;
import com.zk.qpm.utils.DecimalFormatUtil;

public class QPMGetSocTempInfoRenderer extends BaseRenderer {

    @Override
    public String type() {
        return QPMFloatViewType.TYPE_FLOAT_VIEW_SOC_TEMP_VIEW;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.jm_gt_item_floatview_keyvalue;
    }

    @Override
    protected void renderer(View mView) {
        TextView keyView = mView.findViewById(R.id.tv_key);
        TextView valueView = mView.findViewById(R.id.tv_value);
        keyView.setText(R.string.jm_gt_floatview_soc_temp);
        Context context = QPMManager.getInstance().getContext();
        QPMRAnalysisResult analysisResult = QPMRAnalysisManager.getInstance().getJMGTRAnalysisResult();
        final QPMRAnalysisResult.SocTempInfo socTempInfo = analysisResult.socTempInfo;
        final StringBuilder sb = new StringBuilder();
        sb.append(context.getString(R.string.jm_gt_floatview_cpu_temp)).append(DecimalFormatUtil.setScale(socTempInfo.cpuTemp/1000, 1) + "\u2103").append("\n")
                .append(context.getString(R.string.jm_gt_floatview_gpu_temp)).append(DecimalFormatUtil.setScale(socTempInfo.gpuTemp/1000, 1) + "\u2103");
        String data = sb.toString();
        if (!TextUtils.isEmpty(data)) {
            valueView.setText(data);
        }
    }
}
