package backend.chat.service;

import backend.chat.domain.User;
import backend.chat.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    /** 회원 가입
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Long join(User user) {
        return userRepository.save(user).getId();
    }

    /** 로그인
     *
     * @param user
     * @return
     */
    @Override
    public boolean login(User user) {
        if (userRepository.findOne(user).getId() == null){
            return false;
        } else return true;
    }
}
