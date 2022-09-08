package cn.kj0901.tms.base.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * KJ200901
 *
 * @Description : md5加解密
 * @Author : Aedes
 * @Date: 2021/4/6 15:18
 */
public class MD5Util {
    static Digester md5 = new Digester(DigestAlgorithm.MD5);

    public static String enc(String str){
        str+="ABC"; //数据盐
        return md5.digestHex(str);
    }

    public static void main(String[] args) {
        enc("12354");
    }


}

