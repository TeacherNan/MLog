package com.wxl.mlog;

import android.os.Process;

/**
 * Created by wxl on 2016/9/23.
 */
public class RichLog {

    /**
     * The minimum stack trace index
     */
    private static final int MIN_STACK_OFFSET = 3;

    private static RichLog mInstance = new RichLog();

    public static RichLog i() {
        return mInstance;
    }

    private RichLog() {
        addFilterClassName(getClass().getName());
    }

    private String mFilterClassName = "";

    public void addFilterClassName(String filterClassName) {
        mFilterClassName += filterClassName + ", ";
    }

    public String showRichLog() {
        return showRichLog(true);
    }

    public String showRichLog(boolean isShowMoreInfo) {
        return showRichLog(false, isShowMoreInfo);
    }

    private  String showRichLog(boolean isShowPackageName, boolean isShowMoreInfo) {
        StringBuffer printedContent = new StringBuffer();
        StackTraceElement[] traceList = Thread.currentThread().getStackTrace();
        int index = getStackOffset(traceList);
        StackTraceElement callerStackTrace =  traceList[index];
        String className = callerStackTrace.getClassName();
        if (!isShowPackageName) {
            className = getSimpleClassName(className);
        }
        if (isShowMoreInfo) {
            printedContent.append("PID: " + Process.myPid());
        }
        printedContent.append( " Thread: " + Thread.currentThread().getName()  + "  ");
        String lineNum = "";
        if (isShowMoreInfo) {
            lineNum = "line:" + callerStackTrace.getLineNumber();
        }
        printedContent.append(className + "#")
                .append(callerStackTrace.getMethodName() + "(" + lineNum + ")")
                .append("--->");
        return printedContent.toString();
    }

    private String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

    /**
     * get the starting index of the stack trace
     *
     * @param trace the stack trace
     * @return the stack offset
     */
    private int getStackOffset(StackTraceElement[] trace) {
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!mFilterClassName.contains(name)) {
                return i;
            }
        }
        return -1;
    }

}
