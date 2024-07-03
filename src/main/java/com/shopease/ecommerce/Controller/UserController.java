package com.shopease.ecommerce.Controller;

import com.shopease.ecommerce.Model.User;
import com.shopease.ecommerce.Service.Implementation.UserDetailServiceImpl;
import com.shopease.ecommerce.payload.UserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserDetailServiceImpl userDetailService;
    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRequest userRequest){
        userDetailService.addUser(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
