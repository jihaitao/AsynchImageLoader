/**   
 * @Title: DownloadImage.java
 * @Package com.example.desdemo
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 上午9:51:34
 * @version V1.0
 */
package com.dianzhong.acitivity;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

/** 
 * @ClassName: DownloadImage 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 上午9:51:34 
 *  
 */
public class DownloadImage {
    private static final String TAG = DownloadImage.class.getName();
    private FileDES mFileDES;
    private static final String KEY = "jihaitao";
    /** 存放图片的目录 */
    private String mImageDir;
    private CallBack mCallBack;
    
    /** 
     * <p>Title: </p> 
     * <p>Description: </p>  
     */
    public DownloadImage() {
        mImageDir = getImageDir();
        try {
            mFileDES = new FileDES(KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void callback(CallBack callback){
        this.mCallBack = callback;
    }
    
    
    public void download(String urlString,String saveFileName){  
        try {
            URL url = new URL(urlString);  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5 * 1000);  
            conn.setRequestMethod("GET");  
            InputStream inStream = conn.getInputStream();  
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){  
                mFileDES.doEncryptFile(inStream,mImageDir+File.separator+saveFileName);
            }
            mCallBack.onSuccess();
        } catch (Exception e) {
            Log.d(TAG, "Download image error");
            mCallBack.onFaild();
        }
         
    }
    
    public Bitmap getImage(String filename){
        return mFileDES.doDecryptFile(mImageDir+File.separator+filename);
    }
    
    public String getImageDir(){
        String imageDir = Environment.getExternalStorageDirectory().getPath()+File.separator+"test";
        File file = new File(imageDir);
        if (!file.exists()) {
            file.mkdirs();
        }else if(!file.isDirectory()){
            file.mkdirs();
        }
        return imageDir;
    }
    
    public interface CallBack{
        
        public void onSuccess();
        
        public void onFaild();
        
    }
}
