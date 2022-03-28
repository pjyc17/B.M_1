package backend.chat.service;

import backend.chat.domain.ChatRoom;
import backend.chat.domain.Message;
import backend.chat.repository.IMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService implements IMessageService{

    private final IMessageRepository messageRepository;

    /** 메시지 생성
     *
     * @param message
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    /** 메시지 전체 조회
     *
     * @param chatroomId
     * @return
     */
    @Override
    public List<Message> findMessages(Long chatroomId) {
        return messageRepository.findAll(chatroomId);
    }
}
