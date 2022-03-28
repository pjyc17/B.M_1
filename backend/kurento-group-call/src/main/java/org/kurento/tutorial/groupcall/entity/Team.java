package org.kurento.tutorial.groupcall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kurento.tutorial.groupcall.entity.dto.TeamDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter // 테스트 코드 작성을 위해 추가 - 소은
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 테스트 코드 작성을 위해 AUTO 에서 IDENTITY 로 수정 - 소은
    private Long id;    // sql에서는 bigint

    @Column(nullable = false)
    private String teamName;

    // meeting table 과 일대다 관계를 위한 meetings 추가 - 소은
    @JsonIgnore
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Meeting> meetings = new ArrayList<>();

    // userHasTeam 의 team
    @JsonIgnore
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<UserHasTeam> userHasTeamList = new ArrayList<>();

    // 연결 엔티티인 UserHasMeeting 과의 일대다 관계를 위한 추가 - 소은
    @JsonIgnore
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<UserHasMeeting> userHasMeetingList = new ArrayList<>();

    public Team(TeamDto teamDto){
        this.teamName = teamDto.getTeamName();
    }

    public Team(Long id){
        this.id = id;
    }

    public void update(TeamDto teamDto) {
        this.teamName = teamDto.getTeamName();
    }
}