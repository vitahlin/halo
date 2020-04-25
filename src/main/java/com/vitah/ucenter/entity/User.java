package com.vitah.ucenter.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicInsert
@DynamicUpdate
public class User extends AbstractAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "app_id")
    private Integer appId;

    @Column(name = "platform")
    private Integer platform;

    @Column(name = "anonymous")
    private Integer anonymous;

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
