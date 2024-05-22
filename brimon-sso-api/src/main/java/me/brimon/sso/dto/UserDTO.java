package me.brimon.sso.dto;

import lombok.Data;
import me.brimon.sso.entity.User;

@Data
public class UserDTO {
    public UserDTO(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
    }

    public UserDTO(User user, String token) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.token = token;
    }

    private String username;
    private String name;
    private String token;
}
