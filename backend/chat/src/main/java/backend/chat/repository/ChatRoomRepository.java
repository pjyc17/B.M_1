package backend.chat.repository;

import backend.chat.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository implements IChatRoomRepository{

    private final EntityManager em;

    /** 채팅방 생성
     *
     * @param chatroom
     * @return
     */
    @Override
    public ChatRoom save(ChatRoom chatroom) {
        if (chatroom.getId() == null){
            em.persist(chatroom);
        } else {
            em.merge(chatroom);
        }
        return chatroom;
    }

    /** 채팅방 전체 조회
     *
     * @return
     */
    @Override
    public List<ChatRoom> findAll() {
        return em.createQuery("select c from ChatRoom c",ChatRoom.class)
                .getResultList();
    }

    /** 채팅방 제목 조회
     *
     * @param id
     * @return
     */
    @Override
    public String findTitle(Long id) {
        return em.find(ChatRoom.class, id).getTitle();
    }
}
