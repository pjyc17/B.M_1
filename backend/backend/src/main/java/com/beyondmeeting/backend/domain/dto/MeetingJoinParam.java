package com.beyondmeeting.backend.domain.dto;

import com.beyondmeeting.backend.domain.HatColor;
import com.beyondmeeting.backend.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingJoinParam {
    Long meetingId;
    Long userId;
    HatColor hatColor;
}
