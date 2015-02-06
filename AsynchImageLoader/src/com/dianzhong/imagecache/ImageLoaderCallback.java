/**   
 * @Title: ImageLoaderCallback.java
 * @Package com.dianzhong.imagecache
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午1:14:57
 * @version V1.0
 */
package com.dianzhong.imagecache;

import android.graphics.Bitmap;

/** 
 * @ClassName: ImageLoaderCallback 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午1:14:57 
 *  
 */
public interface ImageLoaderCallback {
    
    public void refresh(String url,Bitmap bitmap);

}
