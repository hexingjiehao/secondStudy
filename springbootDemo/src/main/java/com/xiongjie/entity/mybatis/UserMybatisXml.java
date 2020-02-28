package com.xiongjie.entity.mybatis;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by xiongjie on 2018/10/22.
 */
public class UserMybatisXml implements Serializable{

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String passWord;

    public UserMybatisXml() {
    }

    public UserMybatisXml(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
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

    @Override
    public String toString() {
        return "UserMybatis{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

}
