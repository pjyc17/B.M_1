package backend.chat.repository;

import backend.chat.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository{

    private final EntityManager em;

    /** 사용자 생성
     *
     * @param user
     * @return
     */
    @Override
    public User save(User user) {
        if (user.getId() == null){
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    /** 사용자 조회
     *
     * @param user
     * @return
     */
    @Override
    public User findOne(User user) {
        return em.find(User.class, user.getId());
    }
}
