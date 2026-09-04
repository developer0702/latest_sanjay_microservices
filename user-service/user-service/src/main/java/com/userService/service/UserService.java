package com.userService.service;


import com.userService.dto.ResponseData;
import com.userService.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long userId);
    List<User> getAllUser();
    User updateUserById(User user,Long userId);
    void deleteUser(Long userId);

    ResponseData getUserWithDepartment(Long userId);
}
