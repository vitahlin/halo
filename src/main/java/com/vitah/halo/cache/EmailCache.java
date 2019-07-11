package com.vitah.halo.cache;

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

    public void setCode(String codeType, Integer appId, String email) {
        String cacheKey = String.format(CACHE_KEY, codeType, appId.toString(), email);

        String code = RandomString.make(8);
        redisTemplate.opsForValue().set(cacheKey, code);
        redisTemplate.expire(cacheKey, EXPIRE_SECOND, TimeUnit.SECONDS);
    }

    public String getCode(String codeType, Integer appId, String email) {
        String cacheKey = String.format(CACHE_KEY, codeType, appId.toString(), email);
        return redisTemplate.opsForValue().get(cacheKey).toString();
    }
}
