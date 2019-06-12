package com.wang.util

import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec

/***
 *
 *
 * 描    述：DES算法
 *
 * 创 建 者： @author wl
 * 创建时间： 2019/6/2 11:40
 * 创建描述：
 *
 * 修 改 者：
 * 修改时间：
 * 修改描述：
 *
 * 审 核 者：
 * 审核时间：
 * 审核描述：
 *
 */
object Des {

    /**
     * DES加密
     *
     * @param src            待加密数据
     * @param key            加密私钥，长度必须是8的倍数
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws Exception 异常
     */
    @Throws(Exception::class)
    fun encode(src: String, key: String): String {
        try {
            //DES算法要求有一个可信任的随机数源
            val sr = SecureRandom()
            // 从原始密匙数据创建DESKeySpec对象
            val dks = DESKeySpec(key.toByteArray(charset("UTF-8")))
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            // 一个SecretKey对象
            val keyFactory = SecretKeyFactory.getInstance("DES")
            val secureKey = keyFactory.generateSecret(dks)
            // Cipher对象实际完成加密操作
            val cipher = Cipher.getInstance("DES/ECB/PKCS5Padding")
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, sr)
            // 现在，获取数据并加密
            // 正式执行加密操作
            return String(Base64.getEncoder().encode(cipher.doFinal(src.toByteArray())))
        } catch (e: Exception) {
            throw Exception(e)
        }

    }

    /**
     * DES解密
     *
     * @param src            待解密字符串
     * @param key            解密私钥，长度必须是8的倍数
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    @Throws(Exception::class)
    fun decode(src: String, key: String): String {
        try {
            // DES算法要求有一个可信任的随机数源
            val sr = SecureRandom()
            // 从原始密匙数据创建一个DESKeySpec对象
            val dks = DESKeySpec(key.toByteArray(charset("UTF-8")))
            // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
            // 一个SecretKey对象
            val keyFactory = SecretKeyFactory.getInstance("DES")
            val secureKey = keyFactory.generateSecret(dks)
            // Cipher对象实际完成解密操作
            val cipher = Cipher.getInstance("DES/ECB/PKCS5Padding")
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, secureKey, sr)
            // 现在，获取数据并解密
            // 正式执行解密操作
            return String(cipher.doFinal(Base64.getDecoder().decode(src)))
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception(e)
        }
    }
}

fun main() {
    val encode = Des.encode("哈哈", "12345678")
    println(encode)
    val decode = Des.decode(encode, "12345678")
    println(decode)
}