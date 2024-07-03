package com.shopease.ecommerce.Configuration;


import com.shopease.ecommerce.Service.Implementation.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserDetailServiceImpl userDetailService;
    @Autowired
    PasswordEncoder encoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails userDetails = userDetailService.loadUserByUsername(authentication.getName());
        if(encoder.matches(authentication.getCredentials().toString(),userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Invalid Username and Password");
    }

    @Override
    public boolean supports(Class<?> authentication) {

        if (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)){
            return true;
        }
        return false;
    }
}
