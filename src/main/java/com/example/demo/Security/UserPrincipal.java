package com.example.demo.Security;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Objects;

public class UserPrincipal implements UserDetails {
    private Long id;

    private String nameSurname;

    private String login;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;


    public UserPrincipal(long id, String nameSurname, String login,String email, String password ) {
        this.id = id;
        this.login =login;
        this.nameSurname = nameSurname;
        this.password = password;
        this.email = email;
    }

    public static UserPrincipal create(User user) {

        return new UserPrincipal(
                user.getUserId(),
                user.getNameSurname(),
                user.getLogin(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public Long getId() {
        return id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}