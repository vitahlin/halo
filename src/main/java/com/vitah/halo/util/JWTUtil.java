package com.vitah.halo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

/**
 * @author vitah
 */
public class JWTUtil {

    private static final Integer EXPIRE_DAYS = 30;

    /**
     * 生成Token
     *
     * @param secret 加密密钥
     * @param userId 用户ID
     * @return
     */
    public static String genToken(String secret, Integer userId) {
        String token = "";

        try {
            Date nowDate = new Date();
            Date expireDate = DateUtil.getDateAfterDays(EXPIRE_DAYS);

            token = JWT.create()
                .withIssuer("App Server")
                .withSubject(userId.toString())
                .withIssuedAt(nowDate)
                .withExpiresAt(expireDate)
                .withNotBefore(nowDate)
                // Todo: 完善jti字段的生成规则
                .withJWTId(Integer.valueOf(1).toString())
                .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }

    /**
     * Token解析
     *
     * @param secret 加密密钥
     * @param token  token字符串
     * @return
     */
    public static Map<String, Claim> verifyToken(String secret, String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret)).
            withIssuer("App Server").build().verify(token);
        return decodedJWT.getClaims();
    }
}
