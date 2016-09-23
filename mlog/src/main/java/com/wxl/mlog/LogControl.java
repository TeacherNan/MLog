package com.wxl.mlog;

import android.text.TextUtils;
import android.util.Log;


/**
 * Created by wxl on 2016/4/13.
 */
class LogControl {

    private static LogControl mLogControl = new LogControl();
    private Config mConfig = new Config();

    private boolean mLogSwitch = true;

    private static final String TAG = "MLog";



    private LogControl() {
        RichLog.i().addFilterClassName(getClass().getName());
        RichLog.i().addFilterClassName(MLog.class.getName());
    }

    public boolean getLogSwitch() {
        return mLogSwitch;
    }

    public void setLogSwitch(boolean logSwitch) {
        this.mLogSwitch = logSwitch;
    }

    public static LogControl i() {
        return mLogControl;
    }

    public Config config() {
        return mConfig;
    }

    public void error(String message) {
        printLog(LogType.ERROR, message, config());
    }

    public void info(String message) {
        printLog(LogType.INFO, message, config());
    }

    public void warn(String message) {
        printLog(LogType.WARN, message, config());
    }

    public void verbose(String message) {
        printLog(LogType.VERBOSE, message, config());
    }


    public void debug(String message) {
        printLog(LogType.DEBUG, message, config());
    }

    private void printLog(LogType logType, String message, Config config) {
        if (!getLogSwitch()) {
            return;
        }

        switch (logType) {
            case DEBUG:
                Log.d(getTag(), getRichLog(config) + message);
                break;
            case ERROR:
                Log.e(getTag(), getRichLog(config) + message);
                break;
            case INFO:
                Log.i(getTag(), getRichLog(config) + message);
                break;
            case VERBOSE:
                Log.v(getTag(), getRichLog(config) + message);
                break;
            case WARN:
                Log.w(getTag(), getRichLog(config) + message);
                break;
        }
    }

    public String getRichLog(Config config) {
        return RichLog.i().showRichLog(config.isShowMoreInfo());
    }

    enum LogType {
        DEBUG, INFO, WARN, VERBOSE, ERROR
    }

    private String getTag() {
        if (TextUtils.isEmpty(mConfig.getGlobalTag())) {
            return TAG;
        } else {
            return mConfig.getGlobalTag();
        }

    }



}
