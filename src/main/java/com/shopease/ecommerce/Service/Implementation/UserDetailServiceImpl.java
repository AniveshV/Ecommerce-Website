package com.shopease.ecommerce.Service.Implementation;

import com.shopease.ecommerce.Exception.ResourceNotFoundException;
import com.shopease.ecommerce.Model.User;
import com.shopease.ecommerce.Repository.UserRepository;
import com.shopease.ecommerce.Service.UserService;
import com.shopease.ecommerce.payload.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        else{
            throw  new ResourceNotFoundException("User does not exist");
        }
    }

    @Override
    public void addUser(UserRequest userRequest) {
        User user = User.builder().
                username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .authority("USER")
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
        userRepository.save(user);
    }
}
