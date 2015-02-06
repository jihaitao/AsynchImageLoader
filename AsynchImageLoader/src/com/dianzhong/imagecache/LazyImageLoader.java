/**   
 * @Title: LazyImageLoader.java
 * @Package com.dianzhong.imagecache
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午1:16:19
 * @version V1.0
 */
package com.dianzhong.imagecache;

import java.lang.Thread.State;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/** 
 * @ClassName: LazyImageLoader 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午1:16:19 
 *  
 */
public class LazyImageLoader {
    
    private static final int MESSAGE_ID =1;
    private static final String EXTRA_IMAGE_URL = "image_url";
    private static final String EXTRA_IMAGE = "image";
    
    private ImageManager imgManager = null;
    
    private BlockingQueue<String> urlQueue = new ArrayBlockingQueue<String>(50);
    
    private DownloadImageThread mDownloadImgThread = new DownloadImageThread();
    
    private CallbackManager callbackManager = new CallbackManager();
    
    /** 
     * <p>Title: </p> 
     * <p>Description: </p>  
     */
    public LazyImageLoader(Context context) {
        imgManager = new ImageManager(context);
    }
    
   
    
    public Bitmap get(String url,ImageLoaderCallback callback){
        Bitmap bitmap = null;
//        if (imgManager.contians(url)) {
//            bitmap = imgManager.getFromCache(url);
//            if (bitmap != null) {
//                return bitmap;
//            }
//        }else{
//            callbackManager.put(url, callback);
//            startDownloadThred(url);
//        }
        
        callbackManager.put(url, callback);
        startDownloadThred(url);
        
        return bitmap;
    }
    
    private void putUrlToQueue(String url){
        if (!urlQueue.contains(url)) {
            try {
                urlQueue.put(url);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            case MESSAGE_ID:
                Bundle bundle = msg.getData();
                String url = bundle.getString(EXTRA_IMAGE_URL);
                Bitmap bitmap = bundle.getParcelable(EXTRA_IMAGE);
                
                callbackManager.callback(url, bitmap);
                break;

            default:
                break;
            }
        };
    };
    
    private void startDownloadThred(String url){
        this.putUrlToQueue(url);
        State state = mDownloadImgThread.getState();
        if (state == State.NEW) {
            mDownloadImgThread.start();
        }
        //线程已经结束
        else if (state == State.TERMINATED) {
            mDownloadImgThread = new DownloadImageThread();
            mDownloadImgThread.start(); 
        }
    }
    
    private class DownloadImageThread extends Thread{
        private boolean isRun = true;
        
        public void shutdown(){
            isRun = false;
        }
        @Override
        public void run() {
            super.run();
            try {
                while (isRun) {
                    String url = urlQueue.poll();
                    if (url == null) {
                        break;
                    }
                    Bitmap bitmap = imgManager.safeGet(url);
                    
                    Message msg = handler.obtainMessage(MESSAGE_ID);
                    msg.getData().putString(EXTRA_IMAGE_URL, url);
                    msg.getData().putParcelable(EXTRA_IMAGE,bitmap);
                    handler.sendMessage(msg);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }finally{
                shutdown();
            }
            
        }
    }
    

}
