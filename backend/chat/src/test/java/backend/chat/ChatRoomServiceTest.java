package backend.chat;

import backend.chat.domain.ChatRoom;
import backend.chat.repository.ChatRoomRepository;
import backend.chat.service.ChatRoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest
//@Transactional
public class ChatRoomServiceTest {

    @Autowired ChatRoomService chatRoomService;
    @Autowired ChatRoomRepository chatRoomRepository;

    @Test
    public void 채팅방생성() throws Exception{
        // given
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setTitle("roomTest1234");

        // when
        Long resultId = chatRoomService.saveRoom(chatRoom);

        // then : assertEquals() 를 사용해보자
        System.out.println("채팅방생성 TEST");
        System.out.println("chatRoom.getId : " + chatRoom.getId());
        System.out.println("chatRoomService.saveRoom : " + resultId);
    }

    public void 마구마구채팅방생성(){
        ChatRoom chatRoom2 = new ChatRoom();
        chatRoom2.setTitle("roomTest2");
        ChatRoom chatRoom3 = new ChatRoom();
        chatRoom3.setTitle("roomTest3");

        chatRoomService.saveRoom(chatRoom2);
        chatRoomService.saveRoom(chatRoom3);
    }

    @Test
    @Transactional
    public void 채팅방조회() throws Exception{
        // ISSUE : ChatRoomRepository 수정 필요
        // ERROR : org.hibernate.hql.internal.ast.QuerySyntaxException: chatroom is not mapped [select c from chatroom c];
        // SOLUTION : "select c from chatroom c" to "select c from ChatRoom c"
        // ERROR : More than one row with the given identifier was found: 14
        //         같은 채팅방아이디를 가진 메시지가 메시지 테이블에 여러개 존재하면 조회를 할 수 없고 test fail

        // given
        //마구마구채팅방생성();

        // when
        List<ChatRoom> roomList = chatRoomService.findRooms();

        // then
        for (ChatRoom chatroom : roomList) {
            System.out.println(chatroom.getTitle());
        }
    }

    @Test
    @Transactional
    public void 채팅방이름조회() throws Exception{
        // given
        ChatRoom chatRoom4 = new ChatRoom();
        chatRoom4.setTitle("roomTest4");

        // when
        Long resultId = chatRoomService.saveRoom(chatRoom4);
        String resultTitle = chatRoomService.findTitle(resultId);

        // then
        System.out.println("채팅방이름조회 TEST");
        System.out.println("chatRoom.getTitle : " + chatRoom4.getTitle());
        System.out.println("chatRoomService.findTitle : " + resultTitle);
    }
}
