package com.vitah.halo.controller;

import com.vitah.halo.entity.User;
import com.vitah.halo.mapper.UserMapper;
import com.vitah.halo.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vitah
 */
@RestController
public class CheckController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/auth/check", method = RequestMethod.POST)
    public ResponseEntity<Object> resetPassword() {
        User user = authenticationFacade.currentUser();
        return new ResponseEntity<>(UserMapper.INSTANCE.userToDTO(user), HttpStatus.OK);
    }
}
