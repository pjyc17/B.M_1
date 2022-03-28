package backend.chat;

import backend.chat.domain.ChatRoom;
import backend.chat.domain.Message;
import backend.chat.domain.User;
import backend.chat.repository.MessageRepository;
import backend.chat.service.ChatRoomService;
import backend.chat.service.MessageService;
import backend.chat.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest
//@Transactional
public class MessageServiceTest {

    @Autowired MessageService messageService;
    @Autowired MessageRepository messageRepository;
    @Autowired UserService userService;
    @Autowired ChatRoomService chatRoomService;

    @Test
    public void 메시지생성() throws Exception{
        // given
        Message message = new Message();
        message.setContent("안녕하세요?");

        // when
        Long resultId = messageService.saveMessage(message).getId();
        String resultContent = messageService.saveMessage(message).getContent();

        // then
        assertEquals(resultId, message.getId());
        System.out.println("messageService.saveMessage : " + resultContent);

    }

    public void 마구마구메시지생성() {
        // given1 : save User
        User user1 = new User();
        user1.setName("강호동 222");
        User user2 = new User();
        user2.setName("유재석 222");
        User user3 = new User();
        user3.setName("신동엽 222");
        userService.join(user1);
        userService.join(user2);
        userService.join(user3);

        // given2 : save Chatroom
        ChatRoom chatRoom1 = new ChatRoom();
        chatRoom1.setTitle("아는형님 채팅방 222");
        ChatRoom chatRoom2 = new ChatRoom();
        chatRoom2.setTitle("무한도전 채팅방 222");
        ChatRoom chatRoom3 = new ChatRoom();
        chatRoom3.setTitle("마녀사냥 채팅방 222");
        chatRoomService.saveRoom(chatRoom1);
        chatRoomService.saveRoom(chatRoom2);
        chatRoomService.saveRoom(chatRoom3);

        // given3 : save Message
        Message message1 = new Message();
        message1.setContent("JPA 어렵다 222");
        message1.setUser(user1);
        message1.setChatroom(chatRoom1);
        Message message2 = new Message();
        message2.setContent("SPRING 어렵다 222");
        message2.setUser(user2);
        message2.setChatroom(chatRoom1);
        Message message3 = new Message();
        message3.setContent("REACT 어렵다 222");
        message3.setUser(user3);
        message3.setChatroom(chatRoom1);
        messageService.saveMessage(message1);
        messageService.saveMessage(message2);
        messageService.saveMessage(message3);
    }

    @Test
    //@Transactional
    public void 메시지전체조회() throws Exception{
        // ERROR1 : Cannot create TypedQuery for query with more than one return using requested result type
        // SOLUTION1 : m,c 두개를 select 하는 거였는데 m 하나를 select 하는 쿼리문으로 수정
        // ERROR2 : 아무것도 출력되지 않음 => 조인 쿼리문이 틀렸나? => 리포지토리 로직이 틀렸네
        // ERROR3 : could not resolve property: chatroom_id
        // SOLUTION2,3 : 조인 쿼리문 수정
        // 전) "select m from Message m join m.chatroom c where m.id = :messageId and c.id = :chatroomId"
        // 후) "select m from Message m join m.chatroom c where c.id = :chatroomId"

        // given
        //마구마구메시지생성();

        // when
        List<Message> messageList = messageService.findMessages(12L);

        // then
        for (Message message : messageList) {
            System.out.println("채팅방 아이디 : " + message.getChatroom().getId());
            System.out.println("채팅메시지 내용 : " + message.getContent());
        }
    }
}
