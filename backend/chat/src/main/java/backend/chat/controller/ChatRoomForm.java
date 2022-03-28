package backend.chat.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatRoomForm {
    private Long id;
    private String title;
    private Long master_id;
    private Long meeting_id;
}
