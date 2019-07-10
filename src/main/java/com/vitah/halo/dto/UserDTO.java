package com.vitah.halo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vitah
 */
@Setter
@Getter
@ToString
public class UserDTO {

    private Integer id;

    private Integer appId;

    private Integer platform;

    private Integer anonymous;

    private Integer gender;

    private String avatar;

    private String nickname;

    private String email;

    private String country;
}
