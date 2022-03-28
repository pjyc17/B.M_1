package com.beyondmeeting.backend.login.controller;


import com.beyondmeeting.backend.domain.dto.UserDto;
import com.beyondmeeting.backend.login.exception.ResourceNotFoundException;
import com.beyondmeeting.backend.login.model.User;
import com.beyondmeeting.backend.login.repository.UserRepository;
import com.beyondmeeting.backend.login.security.CurrentUser;
import com.beyondmeeting.backend.login.security.UserPrincipal;
import com.beyondmeeting.backend.repository.UserHasTeamRepository;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
/*
@PreAuthorize("hasRole('USER')")
가입 시 제한된 리소스에 접근할 수 있는 ROLE_USER 권한을 회원에게 부여한다.
SpringSecurity 설정에는 접근 제한이 필요한 리소스에 대해서 ROLE_USER 권한을 가져야 접근이 가능하도록 세팅한다.
권한을 가진 회원이 로그인 성공 시엔 리소스에 접근할 수 있는 Jwt 보안 토큰을 발급한다.
코드 작성 위치에 따라 클래스 내 모든 메소드에 적용하거나 메소드별로 권한부여가 가능하다
 */
//@PreAuthorize("hasRole('USER')")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private UserHasTeamRepository userHasTeamRepository;

    // 내 정보 조회
    @GetMapping("/user/me")
    public UserDto getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getName());
        userDto.setUserEmail(user.getEmail());
        userDto.setUserImage(user.getImageUrl());
        userDto.setUserHasTeamList(user.getUserHasTeamList());
        userDto.setUserHasMeetingList(user.getUserHasMeetingList());
//        userDto.setDeleteUser(user.deleteUser);
        return userDto;
    }


    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    // 추후에 데이터 삭제하지 않고 flag 처리 ..
//    @DeleteMapping("/user/{id}")
//    public String deleteUser(@PathVariable Long id){
//        String userName = userRepository.findById(id).get().getName();
//        userRepository.deleteById(id);
//        return userName;
//    }
//
//    @Transactional
//    @PostMapping("/user/delete")
//    public User deleteUser(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserDto userDto){
//        User user = userRepository.findById(userPrincipal.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
//        System.out.println("회원삭제기능입니다아아아ㅏㅏㅏㅏ");
//        user.delete(); //
//        return user;
//    }
}
