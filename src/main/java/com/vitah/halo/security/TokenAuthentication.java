package com.vitah.halo.security;

import com.auth0.jwt.interfaces.Claim;
import com.vitah.halo.entity.User;
import com.vitah.halo.repository.UserRepository;
import com.vitah.halo.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @author vitah
 */
@Component
public class TokenAuthentication {

    private static String UID_CLAIM_KEY = "sub";

    @Autowired
    private UserRepository userRepository;

    public Authentication getAuthentication(String token, String secret) {
        if (token == null || secret == null) {
            return null;
        }

        Map<String, Claim> claimMap = JWTUtil.verifyToken(secret, token);
        if (!claimMap.containsKey(UID_CLAIM_KEY)) {
            return null;
        }

        Integer uid = Integer.valueOf(claimMap.get(UID_CLAIM_KEY).asString());
        User user = userRepository.findById(uid).orElse(null);
        if (user == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
