package com.tgf.study.studyservice.downloadSample;

/**
 * Created by tugaofeng on 17/3/27.
 */
public interface DownloadInterface {

    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
