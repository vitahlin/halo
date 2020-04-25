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
@Entity(name = "UserByAnonymous")
@Table(name = "user_by_anonymous")
@DynamicInsert
@DynamicUpdate
public class UserByAnonymous extends AbstractAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "app_id")
    private Integer appId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "device_id")
    private String deviceId;
}
