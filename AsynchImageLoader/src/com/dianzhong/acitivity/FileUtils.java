/**   
 * @Title: FileUti.java
 * @Package com.example.desdemo
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author jihaitao@weloment.com
 * @date 2015��2��3�� ����5:08:45
 * @version V1.0
 */
package com.dianzhong.acitivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import android.os.Environment;

/** 
 * @ClassName: FileUti 
 * @Description: TODO(������һ�仰��������������) 
 * @author jihaitao@weloment.com
 * @date 2015��2��3�� ����5:08:45 
 *  
 */
public class FileUtils {
    /**
     * 
     * @Title: getSDCard 
     * @Description: ��ȡSD����Ŀ¼
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
