package com.beyondmeeting.backend.domain.dto;

import com.beyondmeeting.backend.domain.*;
import com.beyondmeeting.backend.login.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinUserInfo {
    User user;
    Meeting meeting;
    Team team;
    RoleType roleType;
    HatColor hatColor;
}
