package backend.chat.repository;

import backend.chat.domain.ChatRoom;
import backend.chat.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageRepository implements IMessageRepository{

    private final EntityManager em;

    /** 메시지 생성
     *
     * @param message
     * @return
     */
    @Override
    public Message save(Message message) {
        if (message.getId() == null){
            em.persist(message);
        } else {
            em.merge(message);
        }
        return message;
    }

    /** 메시지 조회
     *
     * @param chatroomId
     * @return
     */
    @Override
    public List<Message> findAll(Long chatroomId) {
        return em.createQuery("select m " +
                        "from Message m join m.chatroom c " +
                        "where c.id = :chatroomId", Message.class)
                        .setParameter("chatroomId", chatroomId)
                        .getResultList();
    }
}
