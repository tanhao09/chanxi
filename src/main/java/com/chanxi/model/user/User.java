package com.chanxi.model.user;

import java.util.Date;

/**
 * Created by ymind on 2018/6/4.
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer age;
    private Integer sex;
    private Integer job;
    private String faceImage;
    private String province;
    private String city;
    private String district;
    private String address;
    private String authSalt;
    private String lastLoginIp;
    private String lastLoginTime;
    private Boolean isDelete;
    private Date registTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthSalt() {
        return authSalt;
    }

    public void setAuthSalt(String authSalt) {
        this.authSalt = authSalt;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (job != null ? !job.equals(user.job) : user.job != null) return false;
        if (faceImage != null ? !faceImage.equals(user.faceImage) : user.faceImage != null) return false;
        if (province != null ? !province.equals(user.province) : user.province != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (district != null ? !district.equals(user.district) : user.district != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (authSalt != null ? !authSalt.equals(user.authSalt) : user.authSalt != null) return false;
        if (lastLoginIp != null ? !lastLoginIp.equals(user.lastLoginIp) : user.lastLoginIp != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(user.lastLoginTime) : user.lastLoginTime != null)
            return false;
        if (isDelete != null ? !isDelete.equals(user.isDelete) : user.isDelete != null) return false;
        return registTime != null ? registTime.equals(user.registTime) : user.registTime == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (faceImage != null ? faceImage.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (authSalt != null ? authSalt.hashCode() : 0);
        result = 31 * result + (lastLoginIp != null ? lastLoginIp.hashCode() : 0);
        result = 31 * result + (lastLoginTime != null ? lastLoginTime.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        result = 31 * result + (registTime != null ? registTime.hashCode() : 0);
        return result;
    }
}
