package com.example.securityweek10.services;

import com.example.securityweek10.models.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Bright
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* Should load user by username from database or source of choice*/
        return new Person("Samuel", "Jachike", "sam@el.com", "12345", "chike");
    }
}
//?? supplies the gotten detail to the class responsible for authentication