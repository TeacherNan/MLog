package com.wxl.mlog;

/**
 * Created by wxl on 2016/4/14.
 */
public final class Config {

    private boolean mShowMoreInfo = false;

    private String mGlobalTag;

    public Config setShowMoreInfo(boolean isShowMoreInfo) {
        this.mShowMoreInfo = isShowMoreInfo;
        return this;
    }


    public Config setGlobalTag(String globalTag) {
        this.mGlobalTag = globalTag;
        return this;
    }

    public String getGlobalTag() {
        return mGlobalTag;
    }

    public boolean isShowMoreInfo() {
        return mShowMoreInfo;
    }



}
