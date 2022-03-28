package com.beyondmeeting.backend.login.repository;

import com.beyondmeeting.backend.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//데이터베이스에서 데이터에 액세스하기 위한 리포지토리 만들기
// UserRepository인터페이스 : User엔터티에 대한 데이터베이스 기능을 제공
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
