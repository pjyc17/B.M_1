package com.beyondmeeting.backend.domain.dto;

import com.beyondmeeting.backend.domain.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserHasTeamDto {
    private Long user;
    private Long team;
    private RoleType roleType = RoleType.MEMBER;

}
