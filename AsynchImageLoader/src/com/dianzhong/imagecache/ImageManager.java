/**   
 * @Title: ImageManager.java
 * @Package com.dianzhong.imagecache
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author jihaitao@weloment.com
 * @date 2015��2��4�� ����1:16:07
 * @version V1.0
 */
package com.dianzhong.imagecache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/** 
 * @ClassName: ImageManager 
 * @Description: TODO(������һ�仰��������������) 
 * @author jihaitao@weloment.com
 * @date 2015��2��4�� ����1:16:07 
 *  
 */
public class ImageManager {
    
    public Map<String, SoftReference<Bitmap>> imgCache;
    private Context mContext;
    
    /** 
     * <p>Title: </p> 
     * <p>Description: </p>  
     */
    public ImageManager(Context context) {
        imgCache = new HashMap<String, SoftReference<Bitmap>>();
        this.mContext = context;
    }
    
    public boolean contians(String url){
        return imgCache.containsKey(url);
    }

    /**
     * 
     * @Title: getFromCache 
     * @Description: �ӻ����л�ȡͼƬ�����Map������û������ļ������ȡ
     * @param @param url
     * @param @return 
     * @return Bitmap
     * @throws
     */
    public Bitmap getFromCache(String url) {
        Bitmap bitmap = null;
        bitmap = getFromMapCache(url);
        if (bitmap == null) {
            bitmap = getFromFile(url);
        }
        return bitmap;
    }
    
    /**
     * 
     * @Title: getFromMapCache 
     * @Description: TODO(������һ�仰�����������������) 
     * @param @param url
     * @param @return 
     * @return Bitmap
     * @throws
     */
    private Bitmap getFromMapCache(String url){
        Bitmap bitmap = null;
        SoftReference<Bitmap> ref = null;
        synchronized (this) {
            ref = imgCache.get(url);
        }
        
        if (null != ref) {
             bitmap = ref.get();
             if (bitmap != null) {
                return bitmap;
            }
        }
        return bitmap;
    }
    
    /**
     * 
     * @Title: getFromFile 
     * @Description: ���ļ������л�ȡͼƬ
     * @param @param url
     * @param @return 
     * @return Bitmap
     * @throws
     */
    private Bitmap getFromFile(String url){
        String filename = this.url2MD5(url); 
        FileInputStream in = null;
        try {
           in = mContext.openFileInput(filename);
           return BitmapFactory.decodeStream(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    //ignore
                }
            }
        }
        return null;
    }

    /**
     * 
     * @Title: getMd5 
     * @Description: ��url����md5����
     * @param @param url
     * @param @return 
     * @return String
     * @throws
     */
    private String url2MD5(String url){
        return MD5Util.getMD5String(url);
    }
    
    public Bitmap downloadImage(String urlString){
        Bitmap bitmap = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String filename = this.url2MD5(urlString);
            String filepath = witeToFile(filename, connection.getInputStream());
            bitmap = BitmapFactory.decodeFile(filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    
    private String witeToFile(String filename,InputStream is){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(mContext.openFileOutput(filename, Context.MODE_PRIVATE));
            
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = bis.read(buffer))>0){
                bos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (bos != null) {
                    bos.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (Exception e2) {
            }
        }
        return mContext.getFilesDir()+File.separator+filename;
    }
    
    
    public Bitmap safeGet(String url){
        Bitmap bitmap = this.getFromFile(url);
        if (null != bitmap) {
            synchronized (this) {
                imgCache.put(url, new SoftReference<Bitmap>(bitmap));
            }
            return bitmap;
        }
        return downloadImage(url);
    }
}
