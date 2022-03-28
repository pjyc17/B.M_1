package org.kurento.tutorial.groupcall.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kurento.tutorial.groupcall.entity.RoleType;

@Getter
@Setter
@NoArgsConstructor
public class UserHasTeamDto {
    private Long user;
    private Long team;
    private RoleType roleType = RoleType.MEMBER;

}
