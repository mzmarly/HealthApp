package com.example.demo.model;

import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.BodyDimensionsInfo.BodyDimensions;
import com.example.demo.model.MonitoredHealthParametersInfo.MonitoredHealthParameters;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "name_surname")
    private String nameSurname;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public Set<BasicUserData> getBasicUserData() {
        return basicUserData;
    }

    public void setBasicUserData(Set<BasicUserData> basicUserData) {
        this.basicUserData = basicUserData;
    }

    public Set<BodyDimensions> getBodyDimensionsons() {
        return bodyDimensionsons;
    }

    public void setBodyDimensionsons(Set<BodyDimensions> bodyDimensionsons) {
        this.bodyDimensionsons = bodyDimensionsons;
    }

    public Set<MonitoredHealthParameters> getMonitoredHealthParameters() {
        return monitoredHealthParameters;
    }

    public void setMonitoredHealthParameters(Set<MonitoredHealthParameters> monitoredHealthParameters) {
        this.monitoredHealthParameters = monitoredHealthParameters;
    }

    @OneToMany
    Set<BasicUserData> basicUserData =new HashSet<>();

    @OneToMany
    Set<BodyDimensions> bodyDimensionsons =new HashSet<>();

    @OneToMany
    Set<MonitoredHealthParameters> monitoredHealthParameters =new HashSet<>();

    public User(long userId, String nameSurname, String login, String email, String password) {
        this.userId = userId;
        this.nameSurname = nameSurname;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
