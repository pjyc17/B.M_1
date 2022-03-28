package backend.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meeting")
@Getter
@Setter
@NoArgsConstructor
public class Meeting {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "meeting")
    private ChatRoom chatroom;

    // meeting id 가져옴
    public Meeting(Long id){
        this.id = id;
    }
}
