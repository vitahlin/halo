package com.vitah.halo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.util.Date;

/**
 * @author vitah
 */
public class JWTUtil {

    private static final Integer EXPIRE_DAYS = 30;

    /**
     * 生成Token
     *
     * @param appKey
     * @param userId
     * @return
     */
    public static String genToken(String appKey, Integer userId) {
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
                .sign(Algorithm.HMAC256(appKey));
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }
}
