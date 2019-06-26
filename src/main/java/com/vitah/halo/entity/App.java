package com.vitah.halo.entity;

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
@Table(name = "app")
@Entity(name = "App")
@DynamicInsert
@DynamicUpdate
public class App extends AbstractAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_cn")
    private String nameCn;

    @Column(name = "app_key")
    private String appKey;

    @Column(name = "bundle_id")
    private String bundleId;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "google_public_key")
    private String googlePublicKey;

    @Column(name = "notifyUrl")
    private String notifyUrl;
}
