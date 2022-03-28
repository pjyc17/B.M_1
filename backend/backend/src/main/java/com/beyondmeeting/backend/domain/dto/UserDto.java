package com.beyondmeeting.backend.domain.dto;

import com.beyondmeeting.backend.domain.Message;
import com.beyondmeeting.backend.domain.UserHasMeeting;
import com.beyondmeeting.backend.domain.UserHasTeam;
import com.beyondmeeting.backend.login.model.AuthProvider;
import com.beyondmeeting.backend.login.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String userEmail;
    private String userImage;
//    private Boolean deleteUser;

    private List<UserHasTeam> userHasTeamList;
    private List<UserHasMeeting> userHasMeetingList;
    private List<Message> messageList;

//    public UserDto(User user) {
//        this.id = user.getId();
//        this.userName = user.getName();
//        this.userEmail = user.getEmail();
//        this.userImage = user.getImageUrl();
//        this.deleteUser = user.getDeleteUser();
//        this.userHasTeamList = user.getUserHasTeamList();
//        this.userHasMeetingList = user.getUserHasMeetingList();
//        this.messageList = user.getMessageList();
//    }

//    public UserDto(User user, List<UserHasTeam> byTeamList) {
//        this.id = user.getId();
//        this.userName = user.getName();
//        this.userEmail = user.getEmail();
//        this.userImage = user.getImageUrl();
////        this.emailVerified = user.getEmailVerified();
////        this.password = user.getPassword();
////        this.provider = user.getProvider();
////        this.providerId = user.getProviderId();
//        this.teamIdList = byTeamList;
//
//    }

}
