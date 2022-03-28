package com.beyondmeeting.backend.domain;

import com.beyondmeeting.backend.login.model.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@SequenceGenerator(name = "UserHasMeeting_SEQ_GENERATOR", sequenceName = "UserHasMeeting_SEQ", initialValue = 1, allocationSize = 1)
public class UserHasMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //@JsonIgnore
    @ManyToOne(targetEntity = Meeting.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    // ERD 수정 : UserHasMeeting.team_id 칼럼 삭제
    //@JsonIgnore
    @ManyToOne(targetEntity = Team.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    private HatColor hat_color;

    private Long speaking_time;
}