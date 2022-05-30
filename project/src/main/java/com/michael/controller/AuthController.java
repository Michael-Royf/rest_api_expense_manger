package com.michael.controller;

import com.michael.entity.AuthModel;
import com.michael.entity.JWTResponse;
import com.michael.entity.User;
import com.michael.entity.UserModel;
import com.michael.security.CustomUserDetailsService;
import com.michael.service.UserService;
import com.michael.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody AuthModel authModel) throws Exception {
        authenticate(authModel.getEmail(), authModel.getPassword());
        //TODO generate jwt token
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authModel.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);


        // SecurityContextHolder.getContext().setAuthentication(authentication);

        JWTResponse response = new JWTResponse(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        } catch (DisabledException e) {
            throw new Exception("User disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel userModel) {
        return new ResponseEntity<>(userService.createUser(userModel), HttpStatus.CREATED);
    }

}
