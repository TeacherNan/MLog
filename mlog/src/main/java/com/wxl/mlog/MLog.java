package com.wxl.mlog;

/**
 * Created by wxl on 2016/4/13.
 */
public final class MLog {

    public static void debug(String message) {
        LogControl.i().debug(message);
    }

    public static void info(String message) {
        LogControl.i().info(message);
    }

    public static void warn(String message) {
        LogControl.i().warn(message);
    }

    public static void verbose(String message) {
        LogControl.i().verbose(message);
    }

    public static void error(String message) {
        LogControl.i().error(message);
    }


    public static void setLogSwitch(boolean logSwitch) {
        LogControl.i().setLogSwitch(logSwitch);
    }

    public static Config config() {
        return LogControl.i().config();
    }


}
