package com.beyondmeeting.backend.domain.dto;

import com.beyondmeeting.backend.domain.MeetingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingCreateParam {
    private String topic;
    private MeetingType meetingType;
    private String teamId;
}
