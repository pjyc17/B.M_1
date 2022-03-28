package com.beyondmeeting.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingFinishParam {
    private Long meetingId;
    // UserId, SpeakingTime 저장을 위해 만들었는데 맴버별 speakTime 기능 구현은 후순위
    // private List<Long> speakingTimeList = new ArrayList<>();
}
