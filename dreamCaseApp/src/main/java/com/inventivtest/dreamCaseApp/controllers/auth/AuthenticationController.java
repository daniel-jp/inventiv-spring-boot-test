package com.inventivtest.dreamCaseApp.controllers.auth;


import com.inventivtest.dreamCaseApp.Security.jwt.TokenService;
import com.inventivtest.dreamCaseApp.dto.userDTO.AuthenticationDTO;
import com.inventivtest.dreamCaseApp.dto.userDTO.LoginResponseDTO;
import com.inventivtest.dreamCaseApp.dto.userDTO.RegisterDTO;
import com.inventivtest.dreamCaseApp.entities.user.User;
import com.inventivtest.dreamCaseApp.exceptions.dreamCaseException.DreamCaseNotFoundException;
import com.inventivtest.dreamCaseApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    //Login__________________________________
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }



//Find by user id_______________________________
    @GetMapping("/{userId}")
    public ResponseEntity<User> getDreamCaseById(@PathVariable String userId) {
        try {
            User registerDTO = userService.findById(userId);
            return ResponseEntity.ok(registerDTO);
        } catch (DreamCaseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    //Find user list_______________________________
    @GetMapping
    public ResponseEntity<List<RegisterDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }


    //Save user_____________________________________
    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody @Valid RegisterDTO data) {
         userService.userRegister(data);
        return ResponseEntity.ok("User registered successfully ✅");
    }


    //Delete user by ID___________________________
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id){
        userService.deleteById(id);
        return  ResponseEntity.ok("User deleted successfully ");
    }


//Update user
    @PutMapping(path = "/{userId}")
    public ResponseEntity updateUser(@RequestBody RegisterDTO registerDTO, @PathVariable String userId) {
        User updatedUser = userService.update(registerDTO, userId);
        return ResponseEntity.ok(updatedUser);
    }
}