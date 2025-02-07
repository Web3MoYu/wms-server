package org.wms.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 加密工具类
 */
public class DigestsUtils {

    public static final DigestAlgorithm ALGORITHM = DigestAlgorithm.SHA1;

    // 迭代次数
    public static final Integer ITERATIONS = 520;

    public static final String DEFAULT_PASSWORD = "dms123456";

    public static final String SALT = "salt";

    public static final String PASSWORD = "password";

    public static Map<String, String> getPassword() {
        return encrypt(DEFAULT_PASSWORD);
    }

    /**
     * @param text 明文
     * @param salt 加密盐
     * @return 密文
     * @Description 讲明文根据盐值进行SHA-1加密
     */
    public static String sha1(String text, String salt) {
        Digester digester = new Digester(ALGORITHM);
        digester.setDigestCount(ITERATIONS);
        digester.setSalt(salt.getBytes());
        return digester.digestHex(text);
    }

    /**
     * @return 盐值
     * @Description 随机生成加密盐值
     */
    public static String createSalt() {
        return BCrypt.gensalt();
    }

    /**
     * @param text 明文
     * @return map集合
     * @Description 返回map，map里有密文和加密盐值
     */
    public static Map<String, String> encrypt(String text) {
        Map<String, String> map = new HashMap<>();
        String salt = createSalt();
        String password = sha1(text, salt);
        map.put(SALT, salt);
        map.put(PASSWORD, password);
        return map;
    }

    /**
     * 匹配密码是否正确
     * @param password 明文
     * @param salt 盐值
     * @param cryptPassword 密文
     * @return boolean
     */
    public static boolean matches(String password, String salt, String cryptPassword) {
        if (StrUtil.isEmpty(password)) {
            return false;
        }
        String sha1 = sha1(password, salt);
        if (Objects.equals(sha1, cryptPassword)) {
            return true;
        }
        if (sha1 == null || cryptPassword == null) {
            return false;
        }
        if (sha1.length() != cryptPassword.length()) {
            return false;
        }
        final int length = sha1.length();
        for (int i = 0; i < length; i++) {
            if (sha1.charAt(i) != cryptPassword.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}