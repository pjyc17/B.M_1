package com.beyondmeeting.backend.repository;

import com.beyondmeeting.backend.domain.Meeting;
import com.beyondmeeting.backend.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByMeeting(Meeting meeting);
}