package com.death.foodorderingprm392.data;

import java.io.Serializable;

public class User implements Serializable {
    private long id;
    private String name;
    private String email;
    private String phone;
    private double walletAmount;
    private int roleId;
    private String roleName;
    private String tempCartMeta;

    public User(long id, String name, String email, String phone, double walletAmount, int roleId, String roleName, String tempCartMeta) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.walletAmount = walletAmount;
        this.roleId = roleId;
        this.roleName = roleName;
        this.tempCartMeta = tempCartMeta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(double walletAmount) {
        this.walletAmount = walletAmount;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTempCartMeta() {
        return tempCartMeta;
    }

    public void setTempCartMeta(String tempCartMeta) {
        this.tempCartMeta = tempCartMeta;
    }
}
