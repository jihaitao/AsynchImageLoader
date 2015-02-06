/**   
 * @Title: SimpleImageLoader.java
 * @Package com.dianzhong.imagecache
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author jihaitao@weloment.com
 * @date 2015��2��4�� ����3:27:04
 * @version V1.0
 */
package com.dianzhong.imagecache;

import android.graphics.Bitmap;
import android.widget.ImageView;

/** 
 * @ClassName: SimpleImageLoader 
 * @Description: TODO(������һ�仰��������������) 
 * @author jihaitao@weloment.com
 * @date 2015��2��4�� ����3:27:04 
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
