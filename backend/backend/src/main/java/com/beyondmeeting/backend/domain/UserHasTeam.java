package com.beyondmeeting.backend.domain;

import com.beyondmeeting.backend.domain.dto.UserHasTeamDto;
import com.beyondmeeting.backend.login.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserHasTeam {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id",unique = true)
    private User user;

    // 팀장인지
    @Enumerated(EnumType.STRING)
    private RoleType roleType;


    public UserHasTeam( User user,Team team, RoleType roleType) {
        this.user = user;
        this.team = team;
        this.roleType = roleType;
    }

    public void update(RoleType roleType) {
        this.roleType = roleType;
    }

    public void update(UserHasTeamDto userHasTeamDto) {
        this.roleType = userHasTeamDto.getRoleType();
    }

    // 팀장변경


//    public UserHasTeam(UserHasTeamDto userHasTeamDto) {
//        //추가ㅏㅏㅏ
//        this.team = userHasTeamDto.getTeam();
//        this.user = userHasTeamDto.getUser();
//        this.role = userHasTeamDto.getRole();
//    }

//    // 변동가능한게 userid,role
//    public void update(UserHasTeamDto userHasTeamDto) {
//        this.user = userHasTeamDto.getUser();
//        this.role = userHasTeamDto.getRole();
//    }
//
//    public UserHasTeam(UserHasTeamDto userHasTeamDto){
//        this.team = userHasTeamDto.getTeam();
//        this.user = userHasTeamDto.getUser();
//        this.role = userHasTeamDto.getRole();
//    }
}
