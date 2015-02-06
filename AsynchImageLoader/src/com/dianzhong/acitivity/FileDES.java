package com.dianzhong.acitivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

public class FileDES {
    
    private static final String TAG = FileDES.class.getSimpleName();    
    /** ���ܽ��ܵ�key */
    private Key mKey;
    /** ���ܵ����� */
    private Cipher mDecryptCipher;
    /** ���ܵ����� */
    private Cipher mEncryptCipher;

    public FileDES(String key) throws Exception {
        initKey(key);
        initCipher();
    }

    /**
     * ����һ�����ܽ��ܵ�key
     * 
     * @param keyRule
     */
    public void initKey(String keyRule) {
        byte[] keyByte = keyRule.getBytes();
        // ����һ���յİ�λ����,Ĭ�������Ϊ0
        byte[] byteTemp = new byte[8];
        // ���û�ָ���Ĺ���ת���ɰ�λ����
        for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
            byteTemp[i] = keyByte[i];
        }
        mKey = new SecretKeySpec(byteTemp, "DES");
    }

    /***
     * ��ʼ����������
     * 
     * @throws Exception
     */
    private void initCipher() throws Exception {
        mEncryptCipher = Cipher.getInstance("DES");
        mEncryptCipher.init(Cipher.ENCRYPT_MODE, mKey);

        mDecryptCipher = Cipher.getInstance("DES");
        mDecryptCipher.init(Cipher.DECRYPT_MODE, mKey);
    }

    /**
     * �����ļ�
     * 
     * @param in
     * @param savePath
     *            ���ܺ󱣴��λ��
     */
    public void doEncryptFile(InputStream in, String savePath) {
        if (in == null) {
            System.out.println("inputstream is null");
            return;
        }
        try {
            CipherInputStream cin = new CipherInputStream(in, mEncryptCipher);
            OutputStream os = new FileOutputStream(savePath);
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = cin.read(bytes)) > 0) {
                os.write(bytes, 0, len);
                os.flush();
            }
            os.close();
            cin.close();
            in.close();
            System.out.println("���ܳɹ�");
        } catch (Exception e) {
            System.out.println("����ʧ��");
            e.printStackTrace();
        }
    }

    /**
     * �����ļ�
     * 
     * @param filePath
     *            ��Ҫ���ܵ��ļ�·��
     * @param savePath
     *            ���ܺ󱣴��λ��
     * @throws FileNotFoundException
     */
    public void doEncryptFile(String filePath, String savePath)
            throws FileNotFoundException {
        doEncryptFile(new FileInputStream(filePath), savePath);
    }

    /**
     * �����ļ�
     * 
     * @param in
     */
    public void doDecryptFile(InputStream in, String savePath) {
        if (in == null) {
            System.out.println("inputstream is null");
            return;
        }
        try {
            CipherInputStream cin = new CipherInputStream(in, mDecryptCipher);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    cin));
            OutputStream os = new FileOutputStream(savePath);
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = cin.read(bytes)) > 0) {
                os.write(bytes, 0, len);
                os.flush();
            }
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            os.close();
            reader.close();
            cin.close();
            in.close();
            System.out.println("���ܳɹ�");
        } catch (Exception e) {
            System.out.println("����ʧ��");
            e.printStackTrace();
        }
    }

    /**
     * �����ļ�
     * 
     * @param filePath
     *            �ļ�·��
     * @throws Exception
     */
    public void doDecryptFile(String filePath, String savePath)
            throws Exception {
        doDecryptFile(new FileInputStream(filePath), savePath);
    }
    
    public Bitmap doDecryptFile(String filePath){
        Bitmap bitmap = null;
        try {
            bitmap = doDecryptFile(new FileInputStream(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    
    private Bitmap doDecryptFile(InputStream in) {
        if (in == null) {
            System.out.println("inputstream is null");
            return null;
        }
        Bitmap bitmap = null;
        try {
            CipherInputStream cin = new CipherInputStream(in, mDecryptCipher);
            bitmap = BitmapFactory.decodeStream(cin);
            System.out.println("���ܳɹ�");
        } catch (Exception e) {
            System.out.println("����ʧ��");
            e.printStackTrace();
        }
        return bitmap;
    }
}
