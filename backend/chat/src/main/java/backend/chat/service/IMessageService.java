package backend.chat.service;

import backend.chat.domain.ChatRoom;
import backend.chat.domain.Message;

import java.util.List;

public interface IMessageService {
    public Message saveMessage(Message message);
    public List<Message> findMessages(Long chatroomId);
}
