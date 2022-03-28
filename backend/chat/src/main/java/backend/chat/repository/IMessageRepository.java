package backend.chat.repository;

import backend.chat.domain.ChatRoom;
import backend.chat.domain.Message;

import java.util.List;

public interface IMessageRepository {
    public Message save(Message message);
    public List<Message> findAll(Long chatroomId);
}

