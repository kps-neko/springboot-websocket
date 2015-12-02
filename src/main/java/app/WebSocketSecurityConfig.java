package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.messaging.simp.SimpMessageType.*;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.security.messaging.web.csrf.CsrfChannelInterceptor;

/**
 * Created by s-wada on 2015/12/01.
 * websocketのアクセス権限設定
 */

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.nullDestMatcher().permitAll()
                .simpSubscribeDestMatchers(
                        "/topic/public/**",
                        "/queue/public/**",
                        "/user/queue/**",
                        "/user/topic/**").permitAll()
                .simpSubscribeDestMatchers(
                        "/topic/stduser/**",
                        "/queue/stduser/**").authenticated()
                .simpSubscribeDestMatchers(
                        "/topic/admin/**",
                        "/queue/admin/**").hasRole("ADMIN")
                .simpDestMatchers("/app/public/**").permitAll()
                .simpDestMatchers("/app/stduser/**").authenticated()
                .simpDestMatchers("/app/admin/**").hasRole("ADMIN")
                // その他のメッセージのうち、MESSAGEとSUBSCRIBEについては通さない。
//                .simpTypeMatchers(MESSAGE, SUBSCRIBE).denyAll()
                // その他のあらゆるメッセージを通さない。
                .anyMessage().denyAll();
    }
}
