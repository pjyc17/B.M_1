package backend.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
WebSocketConfig 역할 :
- ws를 사용하기 위한 config 파일
- stomp 의 엔드포인트 설정 (StompEndpointRegistry)
- 메시지 프레임의 라우팅 방향 지정 (configureMessageBroker)
 */

// https://spring.io/guides/gs/messaging-stomp-websocket/

@Configuration // config class 을 나타내는 annotation
@EnableWebSocketMessageBroker // ws 을 통해 브로커 메시징을 활성화 하기 위해 사용
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/*
	implements WebSocketMessageBrokerConfigurer :
	- ws 연결 속성을 설정
	- ws 클라이언트로부터 받은 메시지 핸들링을 구성하는 메소드들을 정의하기 위해서 상속
	- 그니까, 클라이언트 메시지 핸들링 메소드를 가지는 있는 인터페이스 상속
	 */

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
    // addEndpoint() : ws에 접속하기 위한 endpoint 등록 (예 : http://localhost:8080/ws)

	/*
	registerStompEndpoints
	- 클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 엔드포인트를 등록
	- 엔드포인트 구성에 withSockJS ()를 사용
		(SockJS는 웹 소켓을 지원하지 않는 브라우저에 폴백 옵션을 활성화하는 데 사용)
	/ws
	- Web Socket 클라이언트가 Handshake 를 위해 연결해야하는 엔드 포인트의 url
		(http 통신처럼 3-handshake 후에 socket 으로 업그레이드 하기 때문에?)
	- http://localhost:8080/ws
	*/

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub"); // publisher : message-handling methods 로 라우팅됨
        registry.enableSimpleBroker("/sub"); // subscriber : topic 으로 시작되는 메시지가 메세지브로커로 라우팅됨
    }
    // setApplicationDestinationPrefixes() : /pub 로 접근하는 메시지만 핸들러로 라우팅
    // enableSimpleBroker() : 메모리 기반 메시지 브로커가 /topic 접두사가 붙은 클라이언트로 메시지를 전달할 수 있도록 설정
    //	예 : 클라이언트 A,B,C가 각각 /topic/master, /topic/sub, /topic/master를 구독하고 있을때 /topic/master로 메시지를 전송하면 클라이언트 A,C만 메시지를 받는 구조

	/*
	configureMessageBroker
	- 메세지 브로커 옵션을 설정
	/pub : destination 이 /pub 으로 된 메세지를 @Controller 의 @MessageMapping 으로 라우팅
	/sub : /sub 로 시작하는 목적지를 가진 메세지가 메세지브로커로 라우팅
	*/

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new MyChannelInterceptor());
    }
	/*
	configureClientInboundChannel
	- ChannelInterceptor 등록을 통해 모든 메세지를 직접 가로챌 수 있다
	- MyChannelInterceptor.java
	*/
}
