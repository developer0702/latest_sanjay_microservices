package com.userService.dto;

import com.userService.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResponseData{

    private User user;
    private Department department;
}
