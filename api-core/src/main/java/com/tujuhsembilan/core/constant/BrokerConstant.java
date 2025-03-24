package com.tujuhsembilan.core.constant;

public class BrokerConstant {
    
    public static class Topic {
        public static final String CUSTOMER = "customer-topic";
        public static final String DINNER_TABLE = "dinner-table-topic";
        
    }

    public static class Group {
        public static final String RESERVATION = "reservation-group";
    }

    public static class KeyMessage {
        public static final String CREATE = "create";
        public static final String UPDATE = "update";
        public static final String DELETE = "delete";
    }
}
