/**   
 * @Title: MyApp.java
 * @Package com.dianzhong.imagecache
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午3:21:05
 * @version V1.0
 */
package com.dianzhong.imagecache;

import android.R.layout;
import android.app.Application;
import android.content.Context;

/** 
 * @ClassName: MyApp 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午3:21:05 
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
