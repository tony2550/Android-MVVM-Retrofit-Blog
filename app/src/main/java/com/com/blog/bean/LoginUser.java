package com.com.blog.bean;

import com.com.blog.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginUser {
    private User user;
    private String token;
}
