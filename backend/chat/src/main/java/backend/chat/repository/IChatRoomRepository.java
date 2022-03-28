package backend.chat.repository;

import backend.chat.domain.ChatRoom;

import java.util.List;

public interface IChatRoomRepository {
    public ChatRoom save(ChatRoom chatroom);
    public List<ChatRoom> findAll();
    public String findTitle(Long id);
}