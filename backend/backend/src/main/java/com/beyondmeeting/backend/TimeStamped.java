package com.beyondmeeting.backend;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 상속 했을 때, 컬럼으로 인식하게 함
@EntityListeners(AuditingEntityListener.class) // 생성, 수정시간을 자동으로 반영하도록 설정
public class TimeStamped {

    @CreatedDate // 생성시간
    private LocalDateTime starttime;

    // 현재시간
    private LocalDateTime endtime = LocalDateTime.now();
}
