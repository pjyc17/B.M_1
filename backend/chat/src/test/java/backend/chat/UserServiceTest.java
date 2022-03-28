package backend.chat;

import backend.chat.domain.User;
import backend.chat.repository.UserRepository;
import backend.chat.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest
//@Transactional
// 데이터베이스에 데이터가 실제로 저장되는지 여부를 알고 싶어서 @Transactional 을 주석처리 했다
// Transactional 을 주석처리 하지 않으면 쿼리가 모두 rollback 되버려서 여부를 확인할 수 없다
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception{
        // given
        User user = new User();
        user.setName("userTest1");

        // when
        Long serv_result = userService.join(user);
        Long get_result = user.getId();

        // then
        System.out.println("user.getId : " + get_result);
        System.out.println("userService.join : " + serv_result);
        assertEquals(serv_result, get_result);

    }

    @Test
    @Transactional
    public void 로그인() throws Exception{
        // ERROR : cannot reliably process 'persist' call
        // SOLUTION : @Transactional (기본적으로 JPA 는 transaction 을 기반으로 동작하기 때문)
        // COMMENT : 채팅방 조회 기능과 다르게 em.find() 를 써서 그런지 @Transactional 이 필요했다
        //           이후 채팅방조회,이름조회에도 @Transactional 을 추가해줬고 테스트코드는 모두 정상동작 했다

        // given
        User user = new User();
        user.setName("userTest2");
        userRepository.save(user);

        // when
        User result = userRepository.findOne(user);

        // then
        System.out.println("userName : " + result.getName());
    }
}
