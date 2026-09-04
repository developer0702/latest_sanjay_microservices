package com.userService.service;


import com.userService.dto.Department;
import com.userService.dto.ResponseData;
import com.userService.entity.User;
import com.userService.exception.ResourceNotFoundException;
import com.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId","for this id",userId));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUserById(User user, Long userId) {
        User updateUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId","for this id",userId));
        updateUser.setEmail(user.getEmail());
        updateUser.setName(user.getName());
        updateUser.setPassword(user.getPassword());
        updateUser.setMobileNumber(user.getMobileNumber());
        return updateUser;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId","for this id",userId));
        userRepository.delete(user);
    }

    @Override
    public ResponseData getUserWithDepartment(Long userId) {
        User user = userRepository.findByUserId(userId);

        // Check user
        if (user == null) {
            throw new ResourceNotFoundException("User", "userId", userId);
        }

        // Check departmentId
        if (user.getDepartmentId() == null) {
            throw new ResourceNotFoundException("Department", "departmentId", userId);
        }Department department;

        try {
            department = restTemplate.getForObject("http://department-service/department/" + user.getDepartmentId(),
                    Department.class);

        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Department", "departmentId", user.getDepartmentId());
        }

        // Extra null check
        if (department == null) {
            throw new ResourceNotFoundException("Department", "departmentId", user.getDepartmentId());
        }

        ResponseData details = new ResponseData();
        details.setUser(user);
        details.setDepartment(department);

        return details;
    }
}