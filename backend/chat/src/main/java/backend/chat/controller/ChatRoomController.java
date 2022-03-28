package backend.chat.controller;

import backend.chat.domain.ChatRoom;
import backend.chat.domain.Meeting;
import backend.chat.domain.Message;
import backend.chat.service.IChatRoomService;
import backend.chat.service.IMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@RestController
public class ChatRoomController {

    private final IChatRoomService chatroomService;
    private final IMessageService messageService;

    // paging
    //final int PAGE = 10;

    /** 특정 채팅방 제목 가져오기
     *
     * @param id
     * @return
     */
    @GetMapping("/room/{id}")
    public ResponseEntity<String> roomTitle(@PathVariable long id) {
        String roomTitle = chatroomService.findTitle(id);
        if (roomTitle == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(roomTitle);
    }

    /** 모든 채팅방 목록 가져오기
     *
     * @return
     */
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoom>> room() {
        List<ChatRoom> rooms = chatroomService.findRooms();
        if (rooms == null || rooms.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.status(HttpStatus.OK).body(rooms);

    }

    /** 채팅방 생성하기
     *
     * @param form
     * @return
     */
    @PostMapping("/room")
    public ResponseEntity<Long> createRoom(@RequestBody ChatRoomForm form) {

        Meeting meeting = new Meeting(form.getMeeting_id());

        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setId(form.getId());
        chatRoom.setTitle(form.getTitle());
        chatRoom.setMaster_id(form.getMaster_id());
        chatRoom.setMeeting(meeting);

        long resultOfCreation = chatroomService.saveRoom(chatRoom);
        if (resultOfCreation >= 0)
            return ResponseEntity.status(HttpStatus.OK).body(resultOfCreation);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Long.MIN_VALUE);
    }

    /** 특정 채팅방 아이디를 가진 메시지 모두 가져오기
     *
     * @param id
     * @return
     */
    @GetMapping("/room/message/{id}") // id : message.chatroom_id
    public ResponseEntity<List<Message>> roomInfo(@PathVariable long id) {
        // paging (특정 채팅방 의 메세지 최근 10개만 가져옴)
        // , @RequestParam(value = "page", defaultValue = "0") String page
        //long idx = page.equals("0") ? 0 : Integer.parseInt(page) * PAGE + 1;
        List<Message> msgList = messageService.findMessages(id);
        return ResponseEntity.status(HttpStatus.OK).body(msgList);
    }
}
