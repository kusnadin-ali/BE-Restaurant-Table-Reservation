package com.tujuhsembilan.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tujuhsembilan.core.constant.ApiConstant;

@Component
public class ResponseUtil {

    public static <T> ResponseEntity<Map<String, Object>> success() {
        return success(null);
    }

    public static <T> ResponseEntity<Map<String, Object>> success(T result) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ApiConstant.SUCCESS_CODE);
        response.put("message", "Success");
        if (result != null)
            response.put("result", result);

        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<Map<String, Object>> error(T result, String customCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", customCode);
        response.put("message", "Error");
        if (result != null)
            response.put("result", result);

        return ResponseEntity.status(400).body(response);
    }

    public static <T> ResponseEntity<Map<String, Object>> error(T result, String customCode, Integer statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", customCode);
        response.put("message", "Error");
        if (result != null)
            response.put("result", result);

        return ResponseEntity.status(statusCode).body(response);
    }

    public static <T> ResponseEntity<Map<String, Object>> error(T result, String customCode, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", customCode);
        response.put("message", message);
        if (result != null)
            response.put("result", result);

        return ResponseEntity.status(500).body(response);
    }

    public static <T> ResponseEntity<Map<String, Object>> error(T result, String customCode, String message,
            Integer statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", customCode);
        response.put("message", message);
        if (result != null)
            response.put("result", result);

        return ResponseEntity.status(statusCode).body(response);
    }
}
