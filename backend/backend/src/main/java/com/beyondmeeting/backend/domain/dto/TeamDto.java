package com.beyondmeeting.backend.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TeamDto {
    private Long id;
    private String teamName;
}
