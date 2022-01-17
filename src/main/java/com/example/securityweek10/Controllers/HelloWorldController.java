package com.example.securityweek10.Controllers;


import com.example.securityweek10.models.AuthRequest;
import com.example.securityweek10.models.AuthResponse;
import com.example.securityweek10.services.MyUserDetailsService;
import com.example.securityweek10.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Prosper
@RestController
public class HelloWorldController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userService;

    @Autowired
    JwtUtils jwtTokenUtils;

    @GetMapping("/hello")
    public String hello(){
        return "Hello world";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
