package com.example.demo.domain;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }

   public static Role parseRoleFromString(String role) throws Exception {
        switch (role){
            case("USER") : return Role.USER;
            case("ADMIN") : return Role.ADMIN;
            default: throw new Exception("Can't parse user's role");
        }
    }
}
