package com.vitah.halo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author vitah
 */
@Getter
@Setter
@ToString
@Table(name = "user")
@Entity(name = "User")
public class User extends AbstractAuditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "app_id")
    private Integer appId;

    @Column(name = "platform")
    private Integer platform;

    @Column(name = "is_anonymous")
    private Integer isAnonymous;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;
}
