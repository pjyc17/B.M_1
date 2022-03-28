package backend.chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "chatroom")
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom { // 주체

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;

    private Long master_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
    // Meeting.id 가 ChatRoom 테이블에서 외래키, 연관관계의 주인

//    @OneToMany(mappedBy = "chatroom")
//    private Message message;
    // ChatRoom.id 가 Message 테이블에서 외래키, 연관관계의 거울
    // chatroom 이 delete 되면 message 도 cascade
}
