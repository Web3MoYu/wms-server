package org.wms.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JWTUtils {
    // 私钥
    private static final String SECRET_KEY = "SJD(O!I@#()SKD<?X<?Z<D)P:K@_)#IO)_SI[KDL;AO)PQ@I#FKDJNFKL";

    public static final Integer EXPIRE_TIME = 3;

    public static final JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();

    /**
     * 生成token
     * @param userId 用户id
     * @return String
     */
    public static String creatToken(String userId) {

        JWTCreator.Builder builder = JWT.create();
        builder.withJWTId(UUID.randomUUID().toString())// 设置token唯一标识
                .withSubject(String.valueOf(userId)) // 设置token的主体
                .withIssuer("9622")// 签发者
                .withIssuedAt(new Date()); //签发时间
        // 设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, EXPIRE_TIME);
        builder.withExpiresAt(instance.getTime());
        //签发
        return builder.sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * 生成token
     * @param map 需要封装进token的数据
     * @param userId 用户id
     * @return String
     */
    public static String creatToken(Map<String, Object> map, String userId) {

        JWTCreator.Builder builder = JWT.create();
        builder.withJWTId(UUID.randomUUID().toString())// 设置token唯一标识
                .withSubject(userId) // 设置token的主体
                .withIssuer("lsh")// 签发者
                .withIssuedAt(new Date()) //签发时间
                .withPayload(map); // 存入动态数据
        // 设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, EXPIRE_TIME);
        builder.withExpiresAt(instance.getTime());
        //签发
        return builder.sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * 验证token是否正确
     * @param token token
     * @return DecodedJWT
     * @throws JWTVerificationException
     */
    public static DecodedJWT verify(String token) throws JWTVerificationException {
        return verifier.verify(token);
    }

    /**
     * 从token中获取id
     * @param token token
     * @return String
     */
    public static String getUserId(String token) {
        return verify(token).getSubject();
    }
}
