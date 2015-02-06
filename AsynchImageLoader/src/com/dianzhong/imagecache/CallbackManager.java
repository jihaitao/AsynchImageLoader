/**   
 * @Title: CallbackManager.java
 * @Package com.dianzhong.imagecache
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午1:17:02
 * @version V1.0
 */
package com.dianzhong.imagecache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import android.graphics.Bitmap;

/** 
 * @ClassName: CallbackManager 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午1:17:02 
 *  
 */
public class CallbackManager {
    
    private ConcurrentHashMap<String, List<ImageLoaderCallback>> callbackMap;
    
    /** 
     * <p>Title: </p> 
     * <p>Description: </p>  
     */
    public CallbackManager() {
        callbackMap = new ConcurrentHashMap<String, List<ImageLoaderCallback>>();
    }
    
    
    public void put(String url,ImageLoaderCallback callback){
        if (!callbackMap.contains(url)) {
            callbackMap.put(url,new ArrayList<ImageLoaderCallback>());
            callbackMap.get(url).add(callback);
        }
    }
    
    public void callback(String url,Bitmap bitmap){
        List<ImageLoaderCallback> callbacks = callbackMap.get(url);
        if (null != callbacks) {
            for (ImageLoaderCallback callback : callbacks) {
                callback.refresh(url, bitmap);
            }
            callbacks.clear();
            callbackMap.remove(url);
        }
    }

}
