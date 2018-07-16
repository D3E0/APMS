package com.zust.acm.apms.entity;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "apms")
public class UserEntity {
    private String userId;
    private String userName;
    private String gender;
    private String institute;
    private String profession;

    @Id
    @Column(name = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "institute")
    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Basic
    @Column(name = "profession")
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (institute != null ? !institute.equals(that.institute) : that.institute != null) return false;
        if (profession != null ? !profession.equals(that.profession) : that.profession != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (institute != null ? institute.hashCode() : 0);
        result = 31 * result + (profession != null ? profession.hashCode() : 0);
        return result;
    }
}
