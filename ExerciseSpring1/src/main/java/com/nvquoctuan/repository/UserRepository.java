package com.nvquoctuan.repository;

import com.nvquoctuan.entity.UserEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

  @Query("SELECT u FROM UserEntity u WHERE u.userName = :keyword OR u.firstName = :keyword")
  List<UserEntity> findByUserNameOrFirstName(String keyword, Pageable pageable);

  @Query("SELECT u FROM UserEntity u WHERE u.userName = :keyword OR "
      + "(u.firstName = :keyword OR u.lastName = :keyword OR "
      + "(u.firstName = :keyword AND u.lastName = :keyword))")
  List<UserEntity> findByUserNameOrFullName(String keyword);
}
