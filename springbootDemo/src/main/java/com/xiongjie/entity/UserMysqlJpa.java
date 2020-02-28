package com.xiongjie.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by xiongjie on 2018/10/22.
 */
@Entity
public class UserMysqlJpa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;

    //这个字段不持久化
    @Transient
    private String mail;

    public UserMysqlJpa() {
    }

    public UserMysqlJpa(String userName, String passWord, String mail) {
        this.userName = userName;
        this.passWord = passWord;
        this.mail = mail;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "UserMysqlJpa{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

}
