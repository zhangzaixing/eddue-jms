package com.eddue.jms.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author zzx
 * @Description 对象与字节转换工具
 * @company www.eddue.com
 * @since 2018/11/14 09:53
 */
public class ByteUtils {

    /**
     * 从字节转换为对象
     * @param bytes
     * @return
     */
    public static Object fromByte(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 从对象转换为字节
     * @param obj
     * @return
     */
    public static byte[] toBytes(Object obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos;
            oos  = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            byte[] objMesage = bos.toByteArray();
            return objMesage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
