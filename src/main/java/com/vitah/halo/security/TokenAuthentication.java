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

    @Autowired
    private UserRepository userRepository;


    public Authentication getAuthentication(String token) {
        if (token == null) {
            return null;
        }

        Map<String, Claim> claimMap = JWTUtil.verifyToken("vitah", token);

        if (!claimMap.containsKey("uid")) {
            return null;
        }

        Integer uid = claimMap.get("uid").asInt();
        User user = userRepository.findById(uid).orElse(null);

        if (user == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
