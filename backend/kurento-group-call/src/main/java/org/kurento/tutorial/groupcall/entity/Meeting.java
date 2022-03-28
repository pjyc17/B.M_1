package org.kurento.tutorial.groupcall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@SequenceGenerator(name = "Meeting_SEQ_GENERATOR", sequenceName = "Meeting_SEQ")
// timestamp 로 start, end 받아옴
public class Meeting extends TimeStamped {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Meeting_SEQ_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;

    // team table 과 다대일 관계를 위한 Team 객체 선언 추가 - 소은
    // ERROR 1 : org.hibernate.LazyInitializationException: could not initialize proxy [com.beyondmeeting.backend.domain.Team#8] - no Session
    // SOLUTION 1 : LAZY convert to EAGER
    // ERROR 2 : nested exception is org.hibernate.PersistentObjectException: detached entity passed to persist
    // SOLUTION 2 : cascade = CascadeType.ALL 추가
    // ERROR 3 : Cannot call sendError() after the response has been committed
    // SOLUTION : add @JsonIgnore
    //@JsonIgnore
    @ManyToOne(targetEntity = Team.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    @JsonIgnore
    @OneToMany(mappedBy = "meeting")
    private List<Message> messageList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "meeting")
    private List<UserHasMeeting> userHasMeetingList = new ArrayList<>();

    // 아래 코드를 대신해서 cascade 를 작성한다 (parent : Meeting, child : UserHasMeeting)
    //public void addUserHasMeeting(UserHasMeeting userHasMeeting){
    //    userHasMeetingList.add(userHasMeeting);
    //    userHasMeeting.setMeeting(this);
    //}

    public Meeting(Long id) {
        this.id =id;
    }

    public Meeting(String topic, MeetingType meetingType) {
        this.topic = topic;
        this.meetingType = meetingType;
    }
}