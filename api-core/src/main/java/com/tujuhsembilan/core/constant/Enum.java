package com.tujuhsembilan.core.constant;

public class Enum {
    
    public enum Role {
        CUSTOMER("ROLE_CUSTOMER"), STAFF("ROLE_STAFF"), ADMIN("ROLE_ADMIN");

        private final String value;

        Role(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
