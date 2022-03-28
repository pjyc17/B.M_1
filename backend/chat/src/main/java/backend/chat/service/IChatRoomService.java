package backend.chat.service;

import backend.chat.domain.ChatRoom;

import java.util.List;

public interface IChatRoomService {
    public Long saveRoom(ChatRoom chatroom);
    public List<ChatRoom> findRooms();
    public String findTitle(Long id);
}
