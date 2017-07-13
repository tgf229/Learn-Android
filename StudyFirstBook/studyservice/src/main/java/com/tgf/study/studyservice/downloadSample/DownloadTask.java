package com.tgf.study.studyservice.downloadSample;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tugaofeng on 17/3/27.
 */
public class DownloadTask extends AsyncTask <String,Integer,Integer>{
    private static final String TAG = "DownloadTask";
    private DownloadInterface listener;

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;

    private boolean isCanceled = false;
    private boolean isPaused = false;

    public DownloadTask(DownloadInterface listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        Log.i(TAG, "doInBackground: ");
        long downloadedLength = 0;
        String url = params[0];
        InputStream in = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + url.substring(url.lastIndexOf("/")));
            if (file.exists()){
                downloadedLength = file.length();
            }
            long contentLength = getContentLength(url);
            Log.i(TAG, "downloadedLength: "+downloadedLength);
            Log.i(TAG, "contentLength: "+contentLength);
            if (contentLength == 0){
                return TYPE_FAILED;
            }else if(downloadedLength == contentLength){
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE","bytes="+ downloadedLength +"-")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            if (response != null && response.isSuccessful()){
                in = response.body().byteStream();
                RandomAccessFile savedFile = new RandomAccessFile(file,"rw");
                savedFile.seek(downloadedLength);
                byte[] b =  new byte[1024];
                int len;
                int total= 0;
                while ((len = in.read(b)) != -1){
                    if (isCanceled){
                        return TYPE_CANCELED;
                    }else if(isPaused){
                        return TYPE_PAUSED;
                    }else{
                        total += len;
                        savedFile.write(b,0,len);
                        int progress = (int) ((total+downloadedLength)*100 /contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private long getContentLength(String url){
        long contentLength = 0;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(url)
                .build();
            Response response = client.newCall(request).execute();
            if (response != null && response.isSuccessful()){
                contentLength = response.body().contentLength();
                response.body().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentLength;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.onProgress(values[0]);
    }
}
