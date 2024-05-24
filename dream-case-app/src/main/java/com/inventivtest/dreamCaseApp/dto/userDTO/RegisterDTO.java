package com.inventivtest.dreamCaseApp.dto.userDTO;

import com.inventivtest.dreamCaseApp.entities.user.UserRole;


public record RegisterDTO(String id,String login, String password,  UserRole role) {
}