package com.codeenginestudio.elearning.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeenginestudio.elearning.dao.entity.ClassEntity;
import com.codeenginestudio.elearning.dao.entity.UserEntity;

@Repository
public interface ClassDAO extends JpaRepository<ClassEntity, Long> {

	ClassEntity getClassByClassid(Long classid);

	List<ClassEntity> findByUser(UserEntity user);

	Page<ClassEntity> findByUser(UserEntity userByUserid, Pageable pageable);

}
