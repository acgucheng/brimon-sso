package me.brimon.sso.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "opb_user")
public class User implements UserDetails {
    @Id
    private String username;
    private String name;
    private String password;
    private Date birthdate;
    private String roles;
    private String legalname;
    private String sinno;
    private String address;
    private String phoneNumber;
    private String email;
    private Integer active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
        if (active.equals(1)) {
            return true;
        }else{
            return false;
        }
    }
}
