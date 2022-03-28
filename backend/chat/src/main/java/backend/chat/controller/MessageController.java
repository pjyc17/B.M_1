package backend.chat.controller;

import backend.chat.domain.Message;
import backend.chat.service.IMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

// MessageController 의 역할 - 전달 받은 메시지를 처리하는 컨트롤러


@CrossOrigin(origins = "*")
// 크로스도메인 이슈란 :
// Ajax 등을 통해 다른 도메인의 서버에 url(data)를 호출할 경우
// XMLHttpRequest 는 보안상의 이유로
// 자신과 동일한 도메인으로만 HTTP 요청을 보내도록 제한하고 있어 에러가 발생
// 웹 페이지의 제한된 자원을 외부 도메인에서 접근을 허용해주는 메커니즘
// 기본적으로 '모든 도메인, 모든 요청방식' 에 대해 허용 한다는 뜻
// 용례 : @CrossOrigin(origins = “허용주소:포트”)
@Slf4j  // 추상 로깅 프레임워크
@RequiredArgsConstructor // 초기화 되지않은 final 나, @NonNull 이 붙은 필드에 대해 생성자를 생성 (lombok annotation)
@RestController
// @Controller : View 를 반환하기 위해서 ViewResolver 사용, ViewResolver 설정에 맞게 View 를 찾아 렌더링
// @Controller 와 차이점 : Data 를 반환하기 위해서는 viewResolver 대신에 HttpMessageConverter 가 동작
// HttpMessageConverter 에는 여러 Converter 가 등록되어 있고, 반환해야 하는 데이터에 따라 사용되는 Converter 가 다름
public class MessageController {

    private final IMessageService messageService; // service di
    private final SimpMessagingTemplate template; // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/messaging/core/AbstractMessageSendingTemplate.html#convertAndSend-D-java.lang.Object-

    /** 메시지 전송
     *
     * @param message
     */
    @MessageMapping("/message")
    public void sendMessage(@Payload Message message) {
        log.info("전달 메세지 : " + message);
        // localhost:8080:/pub/message 로 보내는 메시지 매핑, sendMessage call
        // 메시지의 페이로드는 sendMessage 메서드에 의해 전달되는 Message 객체에 바운딩됨

        messageService.saveMessage(message);
        template.convertAndSend("/sub/" + message.getChatroom().getId(), message);
        // convertAndSend :
        // Convert the given Object to serialized form, possibly using a MessageConverter,
        // wrap it as a message and send it to the given destination.
        // using form :
        // public void convertAndSend(D destination, Object payload) throws MessagingException
        // 첫번째 param 에 해당하는 토픽을 구독중인 클라이언트에게 메시지를 전송
        // 첫번째 파라미터 = chatMessage.getChatroomId() = 채팅방 아이디(번호)
    }
}

