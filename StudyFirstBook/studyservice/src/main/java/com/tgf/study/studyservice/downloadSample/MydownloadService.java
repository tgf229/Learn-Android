package com.tgf.study.studyservice.downloadSample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MydownloadService extends Service {
    private static final String TAG = "MydownloadService";
    private DownloadBinder mBinder = new DownloadBinder();

    public MydownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private DownloadInterface listener = new DownloadInterface() {
        @Override
        public void onProgress(int progress) {
            Log.i(TAG, "onProgress: "+progress);
        }

        @Override
        public void onSuccess() {
            Log.i(TAG, "onSuccess: ");
        }

        @Override
        public void onFailed() {
            Log.i(TAG, "onFailed: ");
        }

        @Override
        public void onPaused() {
            Log.i(TAG, "onPaused: ");
        }

        @Override
        public void onCanceled() {
            Log.i(TAG, "onCanceled: ");
        }
    };

    class DownloadBinder extends Binder{
        public void start(String url){
            DownloadTask task = new DownloadTask(listener);
            task.execute(url);
        }
        public void pause(){

        }
        public void cancel(){

        }
    }
}
