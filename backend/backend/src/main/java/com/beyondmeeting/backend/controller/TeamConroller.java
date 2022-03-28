package com.beyondmeeting.backend.controller;

import com.beyondmeeting.backend.domain.RoleType;
import com.beyondmeeting.backend.domain.Team;
import com.beyondmeeting.backend.domain.UserHasTeam;
import com.beyondmeeting.backend.domain.dto.TeamDto;
import com.beyondmeeting.backend.domain.dto.UserHasTeamDto;
import com.beyondmeeting.backend.login.model.User;
import com.beyondmeeting.backend.login.repository.UserRepository;
import com.beyondmeeting.backend.login.security.CurrentUser;
import com.beyondmeeting.backend.login.security.UserPrincipal;
import com.beyondmeeting.backend.repository.TeamRepository;
import com.beyondmeeting.backend.repository.UserHasTeamRepository;
import com.beyondmeeting.backend.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
@RestController
public class TeamConroller {

    private final TeamRepository teamRepository;
    private final UserHasTeamRepository userHasTeamRepository;
    private final UserRepository userRepository;
    private final TeamService teamService;

    // ------------------------------------ CREATE ---------------------------------------
    // 팀추가 - success
    // 로그인만 테스트남음
    @PostMapping("/team")
    public UserHasTeam createTeam(@RequestBody TeamDto teamDto,@CurrentUser UserPrincipal userPrincipal){
//        String msg = "team create success!";
//        try{
//            Team team = new Team(teamDto);
//            // 로그인된사람이 팀장
//            User user = userRepository.findById(userPrincipal.getId()).get();
//            //System.out.printf(user.getName());
//
//            // 일단 테스트용으로 user 아무거나
//            //User user = userRepository.findById(6L).get();
//
//            UserHasTeam userHasTeam = new UserHasTeam(user,team,RoleType.LEADER);
//            teamRepository.save(team);
//
//            // userHasTeamList 추가
//            team.getUserHasTeamList().add(userHasTeam);
//
//            userHasTeamRepository.save(userHasTeam);
//
//        }catch(NullPointerException e){
//            System.out.println("다른 이름을 사용해주세요.");
//            return msg = "다른 이름을 사용해주세요.";
//        }finally {
//
//        }
//
//        return msg;

            Team team = new Team(teamDto);
            // 로그인된사람이 팀장
            User user = userRepository.findById(userPrincipal.getId()).get();

            UserHasTeam userHasTeam = new UserHasTeam(user,team,RoleType.LEADER);
            teamRepository.save(team);

            // userHasTeamList 추가
            team.getUserHasTeamList().add(userHasTeam);

        return userHasTeamRepository.save(userHasTeam);
    }

    // 팀원추가 - success
    @PostMapping("/team/member")
    public String createTeamMember( @RequestBody UserHasTeamDto userHasTeamDto){

        Team team = teamRepository.findById(userHasTeamDto.getTeam()).get();
        User user = userRepository.findById(userHasTeamDto.getUser()).get();
        UserHasTeam userHasTeam = new UserHasTeam(user, team, userHasTeamDto.getRoleType());

        // 팀에 중복된 유저가 들어가지 않게끔
        // 중복된 유저가 없으면

        // userHasTeam 에 teamId로 찾고 거기서 userId가 중복되는지 확인
        // findByTeamAndUser 가 null 이면 insert 값이 존재하면 null

        if(userHasTeamRepository.findAllByTeamAndUser(team,user) == null){
            userHasTeamRepository.save(userHasTeam);
            return "team member add success!!";
        }else return "Failed to add member!! It's duplicate member";

    }

    // ------------------------------------ READ ---------------------------------------

    // 팀조회
    @GetMapping("/teams")
    public List<Team> getTeam(){
        return teamRepository.findAll();
    }

    // 팀원조회 - success
    // id : 팀 id로 팀접근해서 구성원 찾기
    @GetMapping("/team/member/{teamId}")
    public List getTeamUsers(@PathVariable Long teamId){
        Team team = teamRepository.findById(teamId).get();

        return team.getUserHasTeamList();
    }

    // ------------------------------------ UPDATE ---------------------------------------
    // 팀정보 수정
    @PutMapping("/team/{teamId}")
    public String updateTeamName(@PathVariable Long teamId, @RequestBody TeamDto teamDto){
        return teamService.updateTeamName(teamId,teamDto);
    }

    // 팀장수정 - success
    // 팀 Id를 받고, 팀장이 될 사람의 userHasTeamDto Body에 바꿀내용 보내기
    @PutMapping("/team/leader/{teamId}")
    public Long updateTeam(@PathVariable Team teamId, @RequestBody UserHasTeamDto userHasTeamDto){
        teamService.updateLeader(teamId,userHasTeamDto);
        return userHasTeamDto.getUser();
    }

    // ------------------------------------ DELETE ---------------------------------------

    //팀삭제
    @DeleteMapping("/team/{teamId}")
    public String deleteTeam(@PathVariable Long teamId) {
        String teamName = teamRepository.findById(teamId).get().getTeamName();
        teamRepository.deleteById(teamId);
        return teamName;
    }

    // 팀원삭제
    @DeleteMapping("/team/member/{teamId}/{userId}")
    public String deleteMember(@PathVariable Long teamId,@PathVariable Long userId){
        // userHasTeam 에서 찾기
        Team team = teamRepository.findById(teamId).get();
        User user = userRepository.findById(userId).get();

        UserHasTeam userHasTeam = userHasTeamRepository.findAllByTeamAndUser(team,user);
        if(userHasTeam.getRoleType() == RoleType.MEMBER){
            Long userHasTeamId = userHasTeam.getId();
            userHasTeamRepository.deleteById(userHasTeamId);
            return user.getName();
        }
        else return "팀장은 삭제 불가";

    }

}
