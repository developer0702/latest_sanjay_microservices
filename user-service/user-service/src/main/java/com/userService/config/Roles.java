package com.userService.config;

public class Roles {

    public enum UserRole {
        USER_ROLE("USER"),
        ROLE_ADMIN("ADMIN");

        private String role;

        private UserRole(String role) {
            this.role = role;
        }

        public String value() {
            return this.role;
        }
    }
}
