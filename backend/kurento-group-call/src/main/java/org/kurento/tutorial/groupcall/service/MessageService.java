package org.kurento.tutorial.groupcall.service;

import org.kurento.tutorial.groupcall.entity.Meeting;
import org.kurento.tutorial.groupcall.entity.Message;
import org.kurento.tutorial.groupcall.entity.User;
import org.kurento.tutorial.groupcall.repository.MeetingRepository;
import org.kurento.tutorial.groupcall.repository.MessageRepository;
import org.kurento.tutorial.groupcall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MeetingRepository meetingRepository;

    public Message save(String userId, String meetingId, String chatData){
        try{
            System.out.println("userId = " + userId);
            System.out.println("meetingId = " + meetingId);
            System.out.println("chatData = " + chatData);
            System.out.println("userRepository.findById(Long.parseLong(userId)).get() = " + userRepository.findById(Long.parseLong(userId)).get());
            Optional<User> user = userRepository.findById(Long.parseLong(userId));
            Optional<Meeting> meeting = meetingRepository.findById(Long.parseLong(meetingId));
            Message message = new Message();
            message.setMeeting(meeting.get());
            message.setUser(user.get());
            message.setContent(chatData);
            message.setSend_time(LocalDateTime.now());
            return messageRepository.save(message);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return null;
    }
}
