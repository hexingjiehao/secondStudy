package com.xiongjie.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by xiongjie on 2018/10/23.
 */
@Entity
public class RoleShiro implements Serializable{

    @Id
    @GeneratedValue
    private Integer id; // 编号

    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="ShiroRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<PermissionShiro> permissions;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name="ShiroUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<UserShiro> userInfos;// 一个角色对应多个用户

    public RoleShiro() {
    }

    public RoleShiro(String role, String description, Boolean available, List<PermissionShiro> permissions, List<UserShiro> userInfos) {
        this.role = role;
        this.description = description;
        this.available = available;
        this.permissions = permissions;
        this.userInfos = userInfos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<PermissionShiro> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionShiro> permissions) {
        this.permissions = permissions;
    }

    public List<UserShiro> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserShiro> userInfos) {
        this.userInfos = userInfos;
    }
}
