package com.nvquoctuan.repository;

import com.nvquoctuan.entity.UserEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

  @Query("SELECT u FROM UserEntity u WHERE u.userName LIKE CONCAT('%', :keyword, '%') "
      + "OR u.firstName LIKE CONCAT(:keyword, '%')")
  List<UserEntity> findByUserNameOrFirstName(String keyword, Pageable pageable);

  @Query("SELECT u FROM UserEntity u WHERE u.lastName LIKE CONCAT('%', :lastName) "
      + "AND u.firstName LIKE CONCAT(:firstName, '%')")
  List<UserEntity> findByFullName(String lastName, String firstName);

  List<UserEntity> findByUserNameContains(String keyword);
}
