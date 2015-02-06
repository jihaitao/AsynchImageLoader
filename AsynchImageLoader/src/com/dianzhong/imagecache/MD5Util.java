/**   
 * @Title: MD5Utils.java
 * @Package com.dianzhong.imagecache
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午1:41:03
 * @version V1.0
 */
package com.dianzhong.imagecache;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
 * @ClassName: MD5Utils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author jihaitao@weloment.com
 * @date 2015年2月4日 下午1:41:03 
 *  
 */
public class MD5Util {
    
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };

    protected static MessageDigest messagedigest = null;
    
    static{
        try{
         messagedigest = MessageDigest.getInstance("MD5");
        }catch(NoSuchAlgorithmException nsaex){
         System.err.println(MD5Util.class.getName()+"初始化失败，MessageDigest不支持MD5Util。");
         nsaex.printStackTrace();
        }
     }
    
    
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
     }
    
    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
     }
    
    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
     }
    
    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
         appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
     }
    
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
     }

}
