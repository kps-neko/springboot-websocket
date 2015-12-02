package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.security.messaging.util.matcher.MessageMatcher;
import org.springframework.security.messaging.util.matcher.SimpMessageTypeMatcher;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.util.Map;

/**
 * Created by s-wada on 2015/11/27.
 */

@Configuration
@EnableWebSocketMessageBroker
public class WSMessageBrokerConfigurer extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * メッセージブローカを登録します。
     *
     * @param registry メッセージブローカのレジストリ。
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // クライアントがsubscribeする宛先
        // 慣習的に topic は複数の購読者を統一的に扱う場合（クライアント多 : 配信者1）
        // 慣習的に queue は（クライアント1 : 配信者1）
        // 実際の仕様を決めるのはコントローラにつけるアノテーション。
        registry.enableSimpleBroker("/topic", "/queue");
        // クライアントのメッセージの届け先登録
        registry.setApplicationDestinationPrefixes("/app");
        // 個別ユーザへの送信先プレフィクス。
        registry.setUserDestinationPrefix("/user");
    }

    /**
     * websocketのエンドポイントを登録。
     *
     * @param registry エンドポイントレジストリ
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/wsendpoint")
                // 生のwsではなくSockJSプロトコルを使用（ IE9以前に対応 ）する。
                .withSockJS().setClientLibraryUrl("/assets/sockjs-0.3.4.js");
    }

    /**
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(inboundMessageCSRFCheckInterceptor());
    }

    /**
     * clientからsendされてくるStompメッセージのCSRFチェックをするインターセプタ。<br />
     * <br />
     * リンク先のStompのコネクション時に使用されるCSRFチェックインターセプタのほぼ丸パクリ。<br />
     * {@link org.springframework.security.messaging.web.csrf.CsrfChannelInterceptor}
     *
     * @return インターセプタ
     */
    @Bean
    public ChannelInterceptorAdapter inboundMessageCSRFCheckInterceptor() {
        return new ChannelInterceptorAdapter() {
            private final MessageMatcher<Object> matcher = new SimpMessageTypeMatcher(
                    SimpMessageType.MESSAGE);

            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                if (!matcher.matches(message)) {
                    return message;
                }

                Map<String, Object> sessionAttributes = SimpMessageHeaderAccessor
                        .getSessionAttributes(message.getHeaders());
                CsrfToken expectedToken = sessionAttributes == null ? null
                        : (CsrfToken) sessionAttributes.get(CsrfToken.class.getName());

                if (expectedToken == null) {
                    throw new MissingCsrfTokenException(null);
                }

                String actualTokenValue = SimpMessageHeaderAccessor.wrap(message)
                        .getFirstNativeHeader(expectedToken.getHeaderName());

                boolean csrfCheckPassed = expectedToken.getToken().equals(actualTokenValue);
                if (csrfCheckPassed) {
                    return message;
                }
                throw new InvalidCsrfTokenException(expectedToken, actualTokenValue);
            }
        };
    }

}
