package com.beyondmeeting.backend.controller;

import com.beyondmeeting.backend.domain.*;
import com.beyondmeeting.backend.domain.dto.*;
import com.beyondmeeting.backend.login.model.User;
import com.beyondmeeting.backend.login.repository.UserRepository;
import com.beyondmeeting.backend.repository.*;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

//@PreAuthorize("hasRole('USER')")
@RestController
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final UserHasMeetingRepository userHasMeetingRepository;
    private final UserHasTeamRepository userHasTeamRepository;


    // ======================================== Meeting, Attender 조회 ========================================


    /**
     * 회의 전체 리스트 조회
     *
     * @return
     */
    @GetMapping("/meetings")
    public ResponseEntity<List<Meeting>> getMeetings() {
        List<Meeting> meetingList = meetingRepository.findAll();
        if (meetingList == null || meetingList.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.status(HttpStatus.OK).body(meetingList);
    }

    /**
     * 특정 회의 아이디(meetingId)를 갖는 회의 단건 조회
     *
     * @param meetingId
     * @return
     */
    @GetMapping("/meeting/{meetingId}")
    public ResponseEntity<Optional<Meeting>> getMeetingByMeetingId(@PathVariable Long meetingId) {
        Optional<Meeting> meetingData = meetingRepository.findById(meetingId);
        if (meetingData == null)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.status(HttpStatus.OK).body(meetingData);
    }

    /**
     * 특정 팀 아이디(teamId)를 갖는 회의 리스트 조회
     *
     * @param teamId
     * @return
     */
    @GetMapping("meeting/team/{teamId}")
    public ResponseEntity<List<Meeting>> getMeetingsByTeamId(@PathVariable Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        List<Meeting> meetingList = meetingRepository.findAllByTeam(team);
        if (meetingList == null || meetingList.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.status(HttpStatus.OK).body(meetingList);
    }

    /**
     * 회의 참여자 전체 리스트 조회
     *
     * @return
     */
    @GetMapping("/attenders")
    public ResponseEntity<List<UserHasMeeting>> getAttenders() {
        List<UserHasMeeting> UserHasMeetingList = userHasMeetingRepository.findAll();
        if (UserHasMeetingList == null || UserHasMeetingList.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.status(HttpStatus.OK).body(UserHasMeetingList);
    }

    /**
     * 특정 회의 아이디(meetingId)를 갖는 회의 참여자 리스트 조회
     *
     * @param meetingId
     * @return
     */
    @GetMapping("/attender/meeting/{meetingId}")
    public ResponseEntity<List<UserHasMeeting>> getAttendersByMeetingId(@PathVariable Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).get();
        List<UserHasMeeting> userHasMeeting = userHasMeetingRepository.findAllByMeeting(meeting);
        if (userHasMeeting == null)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.status(HttpStatus.OK).body(userHasMeeting);
    }

    /**
     * 내 유저 아이디로 미팅 참여 정보 조회
     *
     * @param userId
     * @return
     */
    @GetMapping("/attender/user/{userId}")
    public ResponseEntity<List<UserHasMeeting>> getAttendersByUserId(@PathVariable Long userId){
        User user = userRepository.findById(userId).get();
        return ResponseEntity.status(HttpStatus.OK).body(user.getUserHasMeetingList());
    }

    /**
     * { 모자색깔, 해당 모자를 쓰고 참여한 회의 전체 시간 } 형태로 리스트 조회 (중복 제거)
     *
     * @param userId
     * @return
     */
    @GetMapping("attender/hat/{userId}")
    public ResponseEntity<ArrayList<HatInfo>> getAttenderWithHat(@PathVariable Long userId) {

        User user = userRepository.findById(userId).get();
        List<UserHasMeeting> targetList = user.getUserHasMeetingList();
        Map<HatColor,Long> resultMap = new HashMap<>();
        ArrayList<HatInfo> resultList = new ArrayList<>();

        for (int n=0 ; n < targetList.size() ; n++) {

            LocalDateTime startTime = targetList.get(n).getMeeting().getStartTime();
            LocalDateTime endTime = targetList.get(n).getMeeting().getEndTime();

            if (endTime != null) {

                HatColor hatColor = targetList.get(n).getHat_color();
                Long time = Duration.between(startTime, endTime).getSeconds();
                if(resultMap.containsKey(hatColor)){
                    resultMap.replace(hatColor,resultMap.get(hatColor)+time) ;
                } else resultMap.put(hatColor,time);

            } else continue;
        }

        for (HatColor hat: resultMap.keySet()) {
            resultList.add(new HatInfo(hat, resultMap.get(hat)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }



    // ======================================== Meeting 생성(Create), 참여, 종료(Update) ========================================


    /**
     * 미팅 생성
     *
     * input :
     * { String topic, String meetingType, Long teamId }
     * description:
     * 미팅 주제(topic), 미팅 타입(meetingType), 팀 아이디(teamId) 을 Meeting Table 에 저장
     *
     * @param meetingCreateParam
     * @return
     */
    @PostMapping("/meeting/create")
    public ResponseEntity<Meeting> createMeeting(@RequestBody MeetingCreateParam meetingCreateParam) {

        // 파라미터로 넘겨받은 meetingParam 각각의 값을 저장해 줄 meeting 객체 생성
        Meeting meeting = new Meeting();

        // teamId 변수에 meetingCreateParam 의 teamId 값을 저장
        Long teamId = Long.valueOf(meetingCreateParam.getTeamId());

        // meeting 객체에 meetingParam 에서 받아온 데이터들을 하나씩 셋팅 (EndTime 은 미팅 종료 시 셋팅하기 위해 null 저장)
        meeting.setTopic(meetingCreateParam.getTopic());
        meeting.setMeetingType(meetingCreateParam.getMeetingType());
        meeting.setTeam(teamRepository.findById(teamId).get());
        meeting.setStartTime(LocalDateTime.now());
        meeting.setEndTime(null);

        // meeting 객체를 데이터베이스에 반영
        meetingRepository.save(meeting);

        // meeting 객체에 담긴 내용 리턴
        return ResponseEntity.status(HttpStatus.OK).body(meeting);
    }

    /**
     * 미팅 참여
     *
     * input :
     * { Long meetingId, Long userId, String hatColor }
     * description :
     * 모자 색깔(hatColor), 미팅 아이디(meetingId), 팀 아이디(teamId), 유저 아이디(userId) 를 UserHasMeeting Table 에 저장
     * return :
     * 팀(teamId)과 유저(userId)를 통해 userHasTeamId (targetId)를 찾고,
     * targetId 를 통해 UserHasTeamRepository 에서 유저가 팀에서 맡은 역할(RoleType)을 가져와서 JoinUserInfo 에 값을 넣는다.
     * 더불어 새로 생성한 UserHasMeeting.getHatColor 를 통해 유저가 미팅에서 맡은 모자(HatColor)을 가져와서 JoinUserInfo 에 값을 넣는다.
     * 반환 값인 JoinUserInfo 를 통해 특정 팀에서 생성된 특정 미팅에 참여하는 유저 정보를 확인할 수 있다.
     *
     * 다시 말해, 유저 정보(User), 유저가 속한 미팅(Meeting), 유저가 속한 팀(Team), 유저가 팀에서 맡은 역할(RoleType), 유저가 미팅에서 맡은 모자(HatColor)를 확인할 수 있다.
     *
     * @param meetingJoinParam
     * @return
     */
    @PostMapping("meeting/join")
    public ResponseEntity<JoinUserInfo> joinMeeting(@RequestBody MeetingJoinParam meetingJoinParam) {

        // ERROR: No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties
        // SOLUTION : getById to findById
        // List<UserHasMeeting> userList = meetingRepository.getById(meetingJoinParam.getMeetingId()).getUserHasMeetingList();
        List<UserHasMeeting> userList = meetingRepository.findById(meetingJoinParam.getMeetingId()).get().getUserHasMeetingList();
        List<Long> userIdList = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            userIdList.add(userList.get(i).getUser().getId());
        }

        // userId, meetingId 변수에 파라미터로 넘겨받은 MeetingJoinParam 값 저장
        Long userId = meetingJoinParam.getUserId(); // input
        Long meetingId = meetingJoinParam.getMeetingId(); // input
        Long teamId = meetingRepository.findById(meetingId).get().getTeam().getId(); // meetingId 로 찾음

        // JoinUserInfo 에 출력하기 위한 정보일 뿐 joinMeeting 로직 수행에는 크게 영향이 없다
        Long targetId = userHasTeamRepository.findAllByTeamAndUser(teamRepository.findById(teamId).get(), userRepository.findById(userId).get()).getId();
        String roleType = String.valueOf(userHasTeamRepository.findById(targetId).get().getRoleType());

        // userId 중복 검증
        if (userIdList.contains(userId)) {
            try {
                throw new Exception("userId 중복이 발생하여 예외를 강제로 발생시켰습니다.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            System.out.println("userId 중복이 발생하여 데이터베이스에 저장하지 않었습니다.");
        }

        else {
            // 유저가 가진 모자 색깔을 저장해줄 UserHasMeeting 객체 생성
            UserHasMeeting userHasMeeting = new UserHasMeeting();

            // UserHasMeeting 객체에 값 셋팅
            userHasMeeting.setUser(userRepository.findById(userId).get());
            userHasMeeting.setMeeting(meetingRepository.findById(meetingId).get());
            userHasMeeting.setTeam(teamRepository.findById(teamId).get());
            userHasMeeting.setHat_color(meetingJoinParam.getHatColor());
            //userHasMeeting.setSpeaking_time(null); speakTime 기능 구현 후순위

            // UserHasMeeting 데이터베이스에 반영
            userHasMeetingRepository.save(userHasMeeting);

            // return 해줄 joinUserInfo 객체 생성 (meeting 참여자의 roleType 와 hatColor 정보를 함께 가지고 있는 객체)
            JoinUserInfo joinUserInfo = new JoinUserInfo();

            // JoinUserInfo 값 셋팅
            joinUserInfo.setUser(userRepository.findById(userId).get());
            joinUserInfo.setMeeting(meetingRepository.findById(meetingId).get());
            joinUserInfo.setTeam(teamRepository.findById(teamId).get());
            joinUserInfo.setHatColor(userHasMeeting.getHat_color());
            joinUserInfo.setRoleType(RoleType.valueOf(roleType));

            // JoinUserInfo 객체 리턴
            return ResponseEntity.status(HttpStatus.OK).body(joinUserInfo);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * 미팅 종료
     *
     * input :
     * Long meetingId
     * description :
     * 미팅 생성 당시 원래 null 값으로 저장된 미팅 종료 시각(endTime)을 Meeting Table 저장
     *
     * @param meetingFinishParam
     * @return
     */
    @PostMapping("meeting/finish")
    public ResponseEntity<Meeting> finishMeeting(@RequestBody MeetingFinishParam meetingFinishParam) {

        // finishMeeting 함수가 호출되는 시각을 endTime 에 저장
        LocalDateTime endTime = LocalDateTime.now();

        // 원래 @RequestBody 에서 MeetingFinishParam 을 받는데 맴버별 speakTime 구현을 우선순위를 낮추기로 해서 
        // @RequestBody 에서 Long meetingId 를 파라미터로 넘겨 받았기 때문에 아래 행을 주석처리 함
        // 주석처리 했었는데 보니까 @RequestBody 에서는 무조건 Json 형태로 요청을 받을 수 있도록 객체로 받는게 좋은가 봄...
        Long meetingId = meetingFinishParam.getMeetingId();

        // 파라미터로 받은 meetingId 값으로 meetingRepository 에서 meeting 객체 찾고 미팅 생성 후 null 값이였던 endTime 값을 셋팅
        Meeting meeting = meetingRepository.findById(meetingId).get();
        meeting.setEndTime(endTime);

        // meeting 객체의 변경된 내용 (endTime setting) 을 데이터베이스에 반영 후 meeting 객체 리턴
        meetingRepository.save(meeting);
        return ResponseEntity.status(HttpStatus.OK).body(meeting);
    }

    @DeleteMapping("delete/meeting/{id}")
    public Long deleteMeeting(@PathVariable Long id){
        String name = meetingRepository.findById(id).get().getTopic();
        meetingRepository.deleteById(id);
        return id;
    }
}