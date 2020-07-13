package com.codeenginestudio.elearning.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeenginestudio.elearning.dao.entity.UserEntity;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, Long> {

	UserEntity getUserByUsername(String username);

	List<UserEntity> findByUsername(String username);

	List<UserEntity> findByEmail(String email);

	List<UserEntity> getUserByRole(Long roleid);

	Page<UserEntity> getUserPageByRole(Long roleid, Pageable pageable);

	UserEntity getUserByUserid(Long userid);

	Page<UserEntity> getUserPageByUsername(String username, Pageable pageable);

}
