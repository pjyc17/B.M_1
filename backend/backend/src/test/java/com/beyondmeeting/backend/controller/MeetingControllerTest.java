package com.beyondmeeting.backend.controller;

import com.beyondmeeting.backend.domain.UserHasMeeting;
import com.beyondmeeting.backend.domain.dto.HatInfo;
import com.beyondmeeting.backend.login.model.User;
import com.beyondmeeting.backend.login.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MeetingControllerTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void 이것저것테스트(){
        Long userId = userRepository.count() + 1;
        System.out.println("userId = " + userId);
    }


    //@Test
    @Transactional
    public void 모자시간리스트테스트(){

        // given
        Long userId = 1L;

        User user = userRepository.findById(userId).get();
        List<UserHasMeeting> targetList = user.getUserHasMeetingList();
//        System.out.println(targetList.get(0).getUser().getEmail());
//        for (UserHasMeeting e: targetList) {
//            System.out.println("e.getHat_color() = " + e.getHat_color());
//        }

//        if (targetList == null){
//            System.out.println("참여한 미팅이 없음");
//        }

        List<HatInfo> resultList = new ArrayList<>();

        for (int n=0 ; n < targetList.size() ; n++) {

            LocalDateTime checkEndTime = targetList.get(n).getMeeting().getEndTime();
//
//            if (checkException == null){
//                System.out.println("미팅이 종료되지 않음");
//            }

            if (checkEndTime != null) {

                HatInfo hatInfo = new HatInfo();

                // Hat Color set
                //resultList.get(0).setHatColor(targetList.get(0).getHat_color());
                hatInfo.setHatColor(targetList.get(n).getHat_color());

                // calc Time set
                LocalDateTime endTime = targetList.get(n).getMeeting().getEndTime();
                LocalDateTime startTime = targetList.get(n).getMeeting().getStartTime();
                //resultList.get(0).setDurationTime(Duration.between(startTime, endTime).getSeconds());
                hatInfo.setDurationTime(Duration.between(startTime, endTime).getSeconds());

                resultList.add(hatInfo);

            } else continue;
        }

        for (HatInfo info : resultList) {
            System.out.println("{ " + info.getHatColor() + " , " + info.getDurationTime() + " }");
        }
    }
}