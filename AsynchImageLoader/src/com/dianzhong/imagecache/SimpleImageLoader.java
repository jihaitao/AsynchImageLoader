/**   
 * @Title: SimpleImageLoader.java
 * @Package com.dianzhong.imagecache
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午3:27:04
 * @version V1.0
 */
package com.dianzhong.imagecache;

import android.graphics.Bitmap;
import android.widget.ImageView;

/** 
 * @ClassName: SimpleImageLoader 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午3:27:04 
 *  
 */
public class SimpleImageLoader {
    public static void showImage(String url,ImageView imageView){
        imageView.setTag(url);
        imageView.setImageBitmap(MyApp.mLazImageLoader.get(url, getCallback(url, imageView)));
    }
    
    private static ImageLoaderCallback getCallback(String url,final ImageView imageView){
        
        return new ImageLoaderCallback( ) {
            
            public void refresh(String url, Bitmap bitmap) {
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        };
    }
}
