package com.beyondmeeting.backend.domain.dto;

import com.beyondmeeting.backend.domain.HatColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HatInfo {
    HatColor hatColor;
    Long durationTime;
}
