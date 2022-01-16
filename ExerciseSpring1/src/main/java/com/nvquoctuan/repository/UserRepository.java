package com.nvquoctuan.repository;

import com.nvquoctuan.dto.UserResponseDto;
import com.nvquoctuan.entity.UserEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

  List<UserEntity> findByFirstNameStartsWith(String firstName, Pageable pageable);
  List<UserEntity> findByLastNameEndsWithAndFirstNameStartsWith(String lastName, String firstName);
  List<UserEntity> findByUserNameContains(String keyword);
}
