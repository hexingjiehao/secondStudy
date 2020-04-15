package com.xiongjie.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by xiongjie on 2018/10/23.
 */
@Entity
public class UserShiro implements Serializable{

    @Id
    @GeneratedValue
    private Integer uid;

    @Column(unique =true)
    private String username;//帐号

    private String name;//名称（昵称或者真实姓名，不同系统不同定义）
    private String password; //密码;
    private String salt;//加密密码的盐
    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    //多表连接查询
    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "ShiroUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<RoleShiro> roleList;// 一个用户具有多个角色

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<RoleShiro> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleShiro> roleList) {
        this.roleList = roleList;
    }


    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }

    public UserShiro(String username, String name, String password, String salt, byte state, List<RoleShiro> roleList) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.state = state;
        this.roleList = roleList;
    }

    public UserShiro() {
    }

}
