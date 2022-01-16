package com.nvquoctuan.repository;

import com.nvquoctuan.entity.UserEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

  List<UserEntity> findByFirstNameStartsWith(String firstName, Pageable pageable);
  List<UserEntity> findByFirstNameStartsWith(String firstName);
  List<UserEntity> findByLastNameStartsWith(String lastName);
  List<UserEntity> findByLastNameStartsWithAndFirstNameContains(String lastName, String firstName);
  List<UserEntity> findByUserNameContains(String keyword);
  boolean existsByUserName(String userName);
}
