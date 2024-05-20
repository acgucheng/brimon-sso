package me.brimon.sso.entity;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.util.Date;

@Data
@Entity
@Table(name = "opb_user")
public class User implements UserDetail{
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
    private String personalDocumentsPath;
    private File personalDocuments;
    private Integer active;

}
