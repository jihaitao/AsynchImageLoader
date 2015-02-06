/**   
 * @Title: FileUti.java
 * @Package com.example.desdemo
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jihaitao@weloment.com
 * @date 2015年2月3日 下午5:08:45
 * @version V1.0
 */
package com.dianzhong.acitivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import android.os.Environment;

/** 
 * @ClassName: FileUti 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author jihaitao@weloment.com
 * @date 2015年2月3日 下午5:08:45 
 *  
 */
public class FileUtils {
    /**
     * 
     * @Title: getSDCard 
     * @Description: 获取SD卡的目录
     * @param @return 
     * @return String
     * @throws
     */
    public static String getSDCard(){
        return Environment.getExternalStorageDirectory().getPath();
    }
    
    public String readFile(String filepath){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";
    }
}
