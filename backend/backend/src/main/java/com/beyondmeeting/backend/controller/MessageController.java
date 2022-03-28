package com.beyondmeeting.backend.controller;

import com.beyondmeeting.backend.domain.Meeting;
import com.beyondmeeting.backend.domain.Message;
import com.beyondmeeting.backend.repository.MeetingRepository;
import com.beyondmeeting.backend.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@PreAuthorize("hasRole('USER')")
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;
    private final MeetingRepository meetingRepository;

    /** 메시지 전체 리스트 조회
     *
     * @return
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(){
        List<Message> messageList = messageRepository.findAll();
        if (messageList == null || messageList.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.status(HttpStatus.OK).body(messageList);
    }

    /** 특정 회의 아이디를 갖는 메시지 리스트 조회
     *
     * @param meetingId
     * @return
     */
    @GetMapping("/message/{meetingId}")
    public ResponseEntity<List<Message>> getMessagesByMeetingId(@PathVariable Long meetingId){
        Meeting meeting = meetingRepository.findById(meetingId).get();
        List<Message> messageList = messageRepository.findByMeeting(meeting);
        if (messageList == null || messageList.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.status(HttpStatus.OK).body(messageList);
    }

}