package com.tujuhsembilan.core.constant;

public class ApiConstant {
    
    public static class ResponseCode {

        public static final String SUCCESS_CODE = "00";
        public static final String ERROR_CODE = "02";
        public static final String NOT_AUTHORIZE_CODE = "03";
    }

    public static class ResponseMessage {
        public static final String SUCCESS_MESSAGE = "Success";
        public static final String ERROR_MESSAGE = "Error";

        public static final String SUCCESS_RETRIEVE_DATA = "Success retrieve data";
        public static final String SUCCESS_CREATE_DATA = "Success create data";
        public static final String SUCCESS_UPDATE_DATA = "Success update data";
        public static final String SUCCESS_REMOVE_DATA = "Success remove data";
        public static final String SUCCESS_FIND_DATA = "Success find data";

        public static final String ERROR_RETRIEVE_DATA = "Error retrieve data";
        public static final String ERROR_CREATE_DATA = "Error create data";
        public static final String ERROR_UPDATE_DATA = "Error update data";
        public static final String ERROR_REMOVE_DATA = "Error remove data";
        public static final String ERROR_DATA_DOESNT_EXIST = "Error data doesn't exist";
        public static final String ERROR_DATA_ALREADY_EXIST = "Error data already exist";
        public static final String ERROR_DATA_ALREADY_RENTED = "Error data already rented";

        public static final String ERROR_INVALID_USERNAME_OR_PASSWORD = "Error invalid username or password";
    }
}
