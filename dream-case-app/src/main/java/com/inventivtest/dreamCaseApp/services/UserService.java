package com.inventivtest.dreamCaseApp.services;

import com.inventivtest.dreamCaseApp.dto.userDTO.RegisterDTO;
import com.inventivtest.dreamCaseApp.entities.user.User;
import com.inventivtest.dreamCaseApp.exceptions.userException.UserAlreadyExistsExeption;
import com.inventivtest.dreamCaseApp.exceptions.userException.UserNotFoundException;
import com.inventivtest.dreamCaseApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }



    public User findById( String id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isEmpty()) {

            throw  new UserNotFoundException("User not found");
        }
        return repository.findById(id).get();
    }


    public List<RegisterDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(user -> new RegisterDTO(
                        user.getId(),
                        user.getLogin(),
                        user.getPassword(),
                        //user.isEnabled(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }



    public ResponseEntity<String> userRegister(RegisterDTO dto) {

        if (this.repository.findByLogin(dto.login())!=null){
            throw new UserAlreadyExistsExeption("A user with " +dto.login()+" already exists");
        }

        User newUser = new User(dto.login(), passwordEncoder.encode(dto.password()),dto.role());
        repository.save(newUser);
        return ResponseEntity.ok().build();
    }


    @Transactional
    public void deleteById(String id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isEmpty() || !userOptional.isPresent()) {
            throw  new UserNotFoundException("The user with "+id+" you are trying to delete is not exist");
        }
        repository.deleteById(id);
    }

    @Transactional
    public User update(RegisterDTO registerDTO, String id) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        existingUser.setId(registerDTO.id());
        return repository.save(existingUser);
    }


}
