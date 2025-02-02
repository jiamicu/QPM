package com.zk.qpm;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

public class QPMException extends Exception {

    public @ExceptionType int type;

    public QPMException(@ExceptionType int type, String message) {
        super(message);
        this.type = type;
    }

    @IntDef({ExceptionType.TYPE_STOP_THREAD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ExceptionType {
        int TYPE_STOP_THREAD = 1;
    }
}
