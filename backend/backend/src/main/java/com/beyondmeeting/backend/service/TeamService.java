package com.beyondmeeting.backend.service;

import com.beyondmeeting.backend.domain.RoleType;
import com.beyondmeeting.backend.domain.Team;
import com.beyondmeeting.backend.domain.UserHasTeam;
import com.beyondmeeting.backend.domain.dto.TeamDto;
import com.beyondmeeting.backend.domain.dto.UserHasTeamDto;
import com.beyondmeeting.backend.login.model.User;
import com.beyondmeeting.backend.login.repository.UserRepository;
import com.beyondmeeting.backend.repository.TeamRepository;
import com.beyondmeeting.backend.repository.UserHasTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final UserHasTeamRepository userHasTeamRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Team updateLeader(Team team, UserHasTeamDto userHasTeamDto) {

        UserHasTeam userHasTeamFrom = userHasTeamRepository.findAllByTeamAndRoleType(team, RoleType.LEADER);
        userHasTeamFrom.update(RoleType.MEMBER);

        User user = userRepository.findById(userHasTeamDto.getUser()).get();
        UserHasTeam userHasTeamTo = userHasTeamRepository.findAllByTeamAndUser(team,user);
        userHasTeamTo.update(userHasTeamDto);

        return team;
    }

    @Transactional
    public String updateTeamName(Long teamId, TeamDto teamDto) {
        Team team = teamRepository.findById(teamId).get();
        team.update(teamDto);

        return team.getTeamName();
    }
}
