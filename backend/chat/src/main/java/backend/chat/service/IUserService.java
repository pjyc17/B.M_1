package backend.chat.service;

import backend.chat.domain.User;

public interface IUserService{
    public Long join(User user);
    public boolean login(User user);
}
