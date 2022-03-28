package backend.chat.repository;

import backend.chat.domain.User;

public interface IUserRepository {
    User save(User user);
    User findOne(User user);
}
