package com.tujuhsembilan.user_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.user_service.dto.User.UserPojo;
import com.tujuhsembilan.user_service.model.User;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndIsDeleteFalse(String username);

    @Query(value = "select u.name from public.user u where u.is_delete = false", nativeQuery = true)
    List<UserPojo> getAll();

    @Query(value = "SELECT u.name, u.email, u.username, u.user_type AS usertype " +
            "FROM public.user u " +
            "WHERE u.user_type IN (:userTypes) AND u.is_delete = false", countQuery = "SELECT COUNT(*) FROM public.user u WHERE u.user_type IN (:userTypes) AND u.is_delete = false", nativeQuery = true)
    Page<UserPojo> getAllByUserTypeWithPagination(@Param("userTypes") List<String> userTypes, Pageable pageable);

}
