/**   
 * @Title: ImageLoaderCallback.java
 * @Package com.dianzhong.imagecache
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author jihaitao@weloment.com
 * @date 2015��2��4�� ����1:14:57
 * @version V1.0
 */
package com.dianzhong.imagecache;

import android.graphics.Bitmap;

/** 
 * @ClassName: ImageLoaderCallback 
 * @Description: TODO(������һ�仰��������������) 
 * @author jihaitao@weloment.com
 * @date 2015��2��4�� ����1:14:57 
 *  
 */
public interface ImageLoaderCallback {
    
    public void refresh(String url,Bitmap bitmap);

}
