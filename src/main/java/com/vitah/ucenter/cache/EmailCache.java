package com.vitah.ucenter.cache;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author vitah
 */
@Component
public class EmailCache {

    public static final String SIGN_UP = "sign_up";
    public static final String RESET_PASSWORD = "reset_password";

    private static final String CACHE_KEY = "email:verify_code:%s:%s:%s";

    private static final Integer EXPIRE_SECOND = 3600;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置验证码
     *
     * @param codeType 验证码类型
     * @param appId    AppId
     * @param email    用户邮箱
     */
    public void setCode(String codeType, Integer appId, String email) {
        String cacheKey = String.format(CACHE_KEY, codeType, appId.toString(), email);

        String code = RandomString.make(8);
        redisTemplate.opsForValue().set(cacheKey, code);
        redisTemplate.expire(cacheKey, EXPIRE_SECOND, TimeUnit.SECONDS);
    }

    /**
     * 获取验证码
     *
     * @param codeType 验证码类型
     * @param appId    AppId
     * @param email    用户邮箱
     * @return
     */
    public String getCode(String codeType, Integer appId, String email) {
        String cacheKey = String.format(CACHE_KEY, codeType, appId.toString(), email);

        Object obj = redisTemplate.opsForValue().get(cacheKey);
        if (obj == null) {
            return "";
        }

        return obj.toString();
    }
}
