package me.brimon.sso.controller;

import jakarta.annotation.security.PermitAll;
import me.brimon.sso.dto.LoginDTO;
import me.brimon.sso.dto.UserDTO;
import me.brimon.sso.entity.User;
import me.brimon.sso.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    @Autowired
    UserController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @PermitAll
    public UserDTO login(@RequestBody LoginDTO loginDTO) {
        System.out.println("Login request received");
        System.out.println(loginDTO.getUsername() + " " + loginDTO.getPassword());
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(),
                loginDTO.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        if(authenticationResponse.isAuthenticated()){
            System.out.println("Authentication response received");
            User user = (User) authenticationResponse.getPrincipal();
            UserDTO userDTO = new UserDTO(user,jwtUtil.generateToken(user));
            System.out.println("UserDTO created:" + userDTO.getUsername() + " " + userDTO.getName() + " " + userDTO.getToken());
            return userDTO;
        }else{
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
