package com.beyondmeeting.backend;

import com.beyondmeeting.backend.domain.Team;
import com.beyondmeeting.backend.domain.UserHasTeam;
import com.beyondmeeting.backend.domain.dto.TeamDto;
import com.beyondmeeting.backend.login.config.AppProperties;
import com.beyondmeeting.backend.domain.Meeting;
import com.beyondmeeting.backend.login.model.User;
import com.beyondmeeting.backend.login.repository.UserRepository;
import com.beyondmeeting.backend.repository.MeetingRepository;
import com.beyondmeeting.backend.repository.TeamRepository;
import com.beyondmeeting.backend.repository.UserHasTeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

//@SpringBootApplication
//@EnableJpaAuditing // Audit : 감시하다! 시간에 대해서 주시하는 역할.. 즉 TimeStamped 클래스를 사용하기 위함 - 진주/0127

@EnableConfigurationProperties(AppProperties.class) //AppProperties 활성화 - 진주/0125
@EnableJpaAuditing
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BackendApplication.class);
//		SpringApplication.run(BackendApplication.class, args);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
	}

}
