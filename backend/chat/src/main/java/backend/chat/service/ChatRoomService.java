package backend.chat.service;

import backend.chat.domain.ChatRoom;
import backend.chat.repository.IChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService implements IChatRoomService{

    private final IChatRoomRepository chatroomRepository;

    /** 채팅방 생성
     *
     * @param chatroom
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Long saveRoom(ChatRoom chatroom) {
        return chatroomRepository.save(chatroom).getId();
    }

    /** 채팅방 조회
     *
     * @return
     */
    @Override
    public List<ChatRoom> findRooms() {
        return chatroomRepository.findAll();
    }

    /** 채팅방 이름 조회
     *
     * @param id
     * @return
     */
    @Override
    public String findTitle(Long id) {
        return chatroomRepository.findTitle(id);
    }
}
