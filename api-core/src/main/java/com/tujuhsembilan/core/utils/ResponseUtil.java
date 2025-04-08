package com.tujuhsembilan.core.utils;

import org.springframework.stereotype.Component;

import com.tujuhsembilan.core.constant.ApiConstant.ResponseCode;
import com.tujuhsembilan.core.constant.ApiConstant.ResponseMessage;
import com.tujuhsembilan.core.dto.ResponseDto;

@Component
public class ResponseUtil {

    public static <T> ResponseDto<T> success() {
        return success(null);
    }

    public static <T> ResponseDto<T> success(T result) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setCode(ResponseCode.SUCCESS_CODE);
        response.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        response.setResult(result);

        return response;
    }

    public static <T> ResponseDto<T> success(T result, String message) {

        ResponseDto<T> response = new ResponseDto<>();
        response.setCode(ResponseCode.SUCCESS_CODE);
        response.setMessage(message);
        response.setResult(result);

        return response;
    }

    public static <T> ResponseDto<T> error(T result) {

        ResponseDto<T> response = new ResponseDto<>();
        response.setCode(ResponseCode.ERROR_CODE);
        response.setMessage(ResponseMessage.ERROR_MESSAGE);
        response.setResult(result);

        return response;
    }

    public static <T> ResponseDto<T> error(T result, String message) {

        ResponseDto<T> response = new ResponseDto<>();
        response.setCode(ResponseCode.ERROR_CODE);
        response.setMessage(message);
        response.setResult(result);

        return response;
    }

    public static <T> ResponseDto<T> error(T result, String code, String message) {

        ResponseDto<T> response = new ResponseDto<>();
        response.setCode(ResponseCode.ERROR_CODE);
        response.setMessage(message);
        response.setResult(result);

        return response;
    }
}
