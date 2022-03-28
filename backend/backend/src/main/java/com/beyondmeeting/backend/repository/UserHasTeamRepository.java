package com.beyondmeeting.backend.repository;

import com.beyondmeeting.backend.domain.RoleType;
import com.beyondmeeting.backend.domain.Team;
import com.beyondmeeting.backend.domain.UserHasTeam;
import com.beyondmeeting.backend.domain.dto.UserHasTeamDto;
import com.beyondmeeting.backend.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserHasTeamRepository extends JpaRepository<UserHasTeam,Long> {

    UserHasTeam findAllByTeamAndRoleType(Team team, RoleType leader);

    UserHasTeam findAllByTeamAndUser(Team team, User user);

//    UserHasTeam findByUser(User user);

}