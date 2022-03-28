package com.beyondmeeting.backend.repository;

import com.beyondmeeting.backend.domain.Meeting;
import com.beyondmeeting.backend.domain.Team;
import com.beyondmeeting.backend.domain.UserHasMeeting;
import com.beyondmeeting.backend.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHasMeetingRepository extends JpaRepository<UserHasMeeting, Long> {
    List<UserHasMeeting> findAllByMeeting(Meeting meeting);
    UserHasMeeting findAllByUser(User user);
    UserHasMeeting findAllByUserAndMeeting(User user, Meeting meeting);
    UserHasMeeting findAllByUserAndTeam(User user, Team team);
    UserHasMeeting findAllByUserAndTeamAndMeeting(User user, Team team, Meeting meeting);
}