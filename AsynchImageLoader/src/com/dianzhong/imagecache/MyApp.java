/**   
 * @Title: MyApp.java
 * @Package com.dianzhong.imagecache
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author jihaitao@weloment.com
 * @date 2015��2��4�� ����3:21:05
 * @version V1.0
 */
package com.dianzhong.imagecache;

import android.R.layout;
import android.app.Application;
import android.content.Context;

/** 
 * @ClassName: MyApp 
 * @Description: TODO(������һ�仰��������������) 
 * @author jihaitao@weloment.com
 * @date 2015��2��4�� ����3:21:05 
 *  
 */
public class MyApp extends Application{
    
    public static LazyImageLoader mLazImageLoader; 
    
    /* (non-Javadoc)
     * <p>Title: onCreate</p> 
     * <p>Description: </p>  
     * @see android.app.Application#onCreate() 
     */
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        
        mLazImageLoader = new LazyImageLoader(this.getApplicationContext());
    }
}
