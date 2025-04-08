package com.tujuhsembilan.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tujuhsembilan.core.dto.ResponseDto;
import com.tujuhsembilan.user_service.dto.User.UserRestaurantCreateDto;
import com.tujuhsembilan.user_service.dto.User.UserUpdateDto;
import com.tujuhsembilan.user_service.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/restaurant")
    @PreAuthorize("@roleEvaluator.hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')")
    public ResponseDto<Object> getRestaurant(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getListStaffUserRestaurant(page, size);
    }

    @GetMapping("/customer")
    @PreAuthorize("@roleEvaluator.hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')")
    public ResponseDto<Object> getCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getListCustomer(page, size);
    }

    @PutMapping("/edit")
    public ResponseDto<Object> editUserDetail(
            @RequestBody UserUpdateDto request) {
        return userService.updateDetailUser(request);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("@roleEvaluator.hasRole('ROLE_ADMIN')")
    public ResponseDto<Object> deleteUserDetail(@PathVariable String username) {
        return userService.deleteUser(username);
    }

    @PostMapping("/restaurant/add")
    @PreAuthorize("@roleEvaluator.hasRole('ROLE_ADMIN')")
    public ResponseDto<Object> addRestaurant(@RequestBody UserRestaurantCreateDto request) {
        return userService.addStaffOrAdminRestaurant(request);
    }
}
