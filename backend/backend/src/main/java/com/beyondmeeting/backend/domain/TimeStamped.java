package com.beyondmeeting.backend.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// getter, setter 수정
@Getter
@Setter
@MappedSuperclass // 상속 했을 때, 컬럼으로 인식하게 함
@EntityListeners(AuditingEntityListener.class) // 생성, 수정시간을 자동으로 반영하도록 설정
public class TimeStamped {

    // 생성시간
    @CreatedDate
    private LocalDateTime startTime;

    // 현재시간
    private LocalDateTime endTime = LocalDateTime.now();
}

// meeting 테이블 컬럼 erd 에 명세된 대로 표시하기 위해 변수 수정 - 소은