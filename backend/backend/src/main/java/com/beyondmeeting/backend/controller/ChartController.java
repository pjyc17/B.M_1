package com.beyondmeeting.backend.controller;

import com.beyondmeeting.backend.domain.HatColor;
import com.beyondmeeting.backend.domain.UserHasMeeting;
import com.beyondmeeting.backend.domain.dto.HatInfo;
import com.beyondmeeting.backend.login.exception.ResourceNotFoundException;
import com.beyondmeeting.backend.login.model.User;
import com.beyondmeeting.backend.login.repository.UserRepository;
import com.beyondmeeting.backend.login.security.CurrentUser;
import com.beyondmeeting.backend.login.security.UserPrincipal;
import com.beyondmeeting.backend.repository.MeetingRepository;
import com.beyondmeeting.backend.repository.TeamRepository;
import com.beyondmeeting.backend.repository.UserHasMeetingRepository;
import com.beyondmeeting.backend.repository.UserHasTeamRepository;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


//@PreAuthorize("hasRole('USER')")
@RestController
@RequiredArgsConstructor
public class ChartController {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final UserHasMeetingRepository userHasMeetingRepository;
    private final UserHasTeamRepository userHasTeamRepository;
    /**
     내 유저 아이디로 미팅 참여 정보 조회
     --> YYYYMM 기준으로 미팅참여횟수 조회
     */
    @GetMapping("/attender/date/{userId}")
    public ArrayList<JSONObject> getAttenderDateByUserId(@PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        List<UserHasMeeting> meeting = user.getUserHasMeetingList();
        Map<Integer, Integer> hashMap = new HashMap<>();

        //첫번째 값
        if (meeting.size() >= 1) {
            String[] dateSplitFirst = String.valueOf(meeting.get(0).getMeeting().getStartTime()).split("-");
            Integer yearFirst = Integer.parseInt(dateSplitFirst[0].substring(2));
            Integer monthFirst = Integer.parseInt(dateSplitFirst[1]);
            Integer datesFirst = yearFirst*100 + monthFirst;
            hashMap.put(datesFirst, 1);
        }

        for (int i = 1; i < meeting.size(); i++) {
            String[] dateSplit = String.valueOf(meeting.get(i).getMeeting().getStartTime()).split("-");
            Integer year = Integer.parseInt(dateSplit[0].substring(2));
            Integer month = Integer.parseInt(dateSplit[1]);
            Integer dates = year*100 + month;

            if (hashMap.containsKey(dates)) {
                int val = hashMap.get(dates);
                hashMap.remove(dates);
                hashMap.put(dates, val + 1);
            } else hashMap.put(dates, 1);
        }

        // key순으로 정렬
//        Object [] mapKey = hashMap.keySet().toArray();
//        Arrays.sort(mapKey);

        List<Integer> keysetList = new ArrayList<>(hashMap.keySet());
        Collections.sort(keysetList);
//        keysetList.sort(Collections.reverseOrder()); // 내림차순
        
        
//        Collections.sort(keysetList, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return hashMap.get(o1).compareTo(hashMap.get(o2));
//            }
//        });
//        HatColor longHat = keysetList.get(0);
//        Long longHatTime = resultMap.get(longHat);

//        for(Integer e : keysetList){
//            System.out.println(e+","+hashMap.get(e));
//        }

        ArrayList<JSONObject> list = new ArrayList<>();
        JSONObject temp = new JSONObject();
        temp.put("argument","START");
        temp.put("value", 0);
        list.add(temp);

        for (Integer nkey : keysetList) {
            temp = new JSONObject();

            String key1 = String.valueOf(nkey).substring(0,2);
            String key2 = String.valueOf(nkey).substring(2,4);
            temp.put("argument", key1+"년 "+key2+"월");
            temp.put("value", hashMap.get(nkey));

            list.add(temp);
        }

        return list;

        // hashmap을 json 객체로 변환
//        JSONObject json = new JSONObject(temp);

//        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // 나의 주된 모자
    @GetMapping("/hatchart/longtime")
    public HatInfo longTimeHat(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
//        User user = userRepository.findById(userId).get();
        List<UserHasMeeting> targetList = user.getUserHasMeetingList();
        Map<HatColor, Long> resultMap = new HashMap<>();
        ArrayList<HatInfo> resultList = new ArrayList<>();

        for (int n = 0; n < targetList.size(); n++) {

            LocalDateTime startTime = targetList.get(n).getMeeting().getStartTime();
            LocalDateTime endTime = targetList.get(n).getMeeting().getEndTime();

            if (endTime != null) {

                HatColor hatColor = targetList.get(n).getHat_color();
                Long time = Duration.between(startTime, endTime).getSeconds();
                if (resultMap.containsKey(hatColor)) {
                    resultMap.replace(hatColor, resultMap.get(hatColor) + time);
                } else resultMap.put(hatColor, time);

            } else continue;
        }

        // 정렬
        List<HatColor> keysetList = new ArrayList<>(resultMap.keySet());
        Collections.sort(keysetList, new Comparator<HatColor>() {
            @Override
            public int compare(HatColor o1, HatColor o2) {
                return resultMap.get(o2).compareTo(resultMap.get(o1));
            }
        });

        for (HatColor hat : keysetList) {
            System.out.println("정렬" + hat + "," + resultMap.get(hat));
        }

        HatColor longHat = keysetList.get(0);
        Long longHatTime = resultMap.get(longHat);

        return new HatInfo(longHat, longHatTime);
    }

    @GetMapping("/meetings/count")
    public Integer myMeetingCount(@CurrentUser UserPrincipal userPrincipal){
        //findAllByUser
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        List<UserHasMeeting> targetList = user.getUserHasMeetingList();
        return targetList.size();
    }
}
