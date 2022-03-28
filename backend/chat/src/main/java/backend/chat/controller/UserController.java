package backend.chat.controller;

import backend.chat.domain.User;
import backend.chat.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*")
@Slf4j
//@RequestMapping("/api/chat")
@RequiredArgsConstructor
//@RestController
public class UserController {

    private final IUserService userService;

    /** 회원 가입
     *
     * @param user
     * @return
     */
    //@PostMapping("/user")
    public ResponseEntity<User> joinUser(@RequestBody User user) {
        long resultOfCreation = userService.join(user);
        if (resultOfCreation >= 0)
            return ResponseEntity.status(HttpStatus.OK).body(user);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 로그인
     *
     * @param user
     * @return
     */
    //@PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean resultOfLogin = userService.login(user);
        if (resultOfLogin)
            return ResponseEntity.status(HttpStatus.OK).body(user.getName());
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}