package com.tujuhsembilan.core.component;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RoleEvaluator {

    private final HttpServletRequest request;

    public RoleEvaluator(HttpServletRequest request) {
        this.request = request;
    }

    public boolean hasRole(String role) {
        String headerRole = request.getHeader("x-role");
        return headerRole != null && headerRole.equals(role);
    }

    public boolean hasAnyRole(String... roles) {
        String headerRole = request.getHeader("x-role");
        if (headerRole == null) {
            return false;
        }
        List<String> roleList = Arrays.asList(roles);
        return roleList.contains(headerRole);
    }
}