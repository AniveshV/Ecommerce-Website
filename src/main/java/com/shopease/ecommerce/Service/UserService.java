package com.shopease.ecommerce.Service;

import com.shopease.ecommerce.payload.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public void addUser(UserRequest userRequest);

}
