package com.tujuhsembilan.core.constant;

import org.springframework.stereotype.Component;

import com.tujuhsembilan.core.constant.Enum.Role;

@Component
public class RoleComponent {
    public static final String ADMIN = Role.ADMIN.getValue();
    public static final String STAFF = Role.STAFF.getValue();
    public static final String CUSTOMER = Role.CUSTOMER.getValue();
}
