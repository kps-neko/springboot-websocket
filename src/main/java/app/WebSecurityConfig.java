package app;

import app.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // WebSocketもしくはWebSocketエミュレーションの
        // - CSRFチェック
        // - 権限設定
        // についてはSebSocketSecurityConfigクラスで行うので、ここでは全スルーする。
        http.csrf()
                .ignoringAntMatchers("/wsendpoint/**")
                // wsエミュレーション用にiframeはsame origin policyで保護される。
                .and().headers().frameOptions().sameOrigin()
                // wsには誰でも接続可能。（ただし、SebSocketSecurityConfigで、ログイン状態や権限に応じてAPIへのアクセス権変わる）
                .and().authorizeRequests().antMatchers("/wsendpoint/**").permitAll();

        // ほぼnumadate氏の実装のままです。
        http
                .authorizeRequests()
                .antMatchers("/", "/signup", "/websocket/**", "/assets/**").permitAll() // 認証なしでアクセスできるパスの指定
                .antMatchers("/admin/**").hasRole("ADMIN")     // adminの場合ロールがADMINの場合のみ許可する
                // その他のリクエストは認証が必要
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // ログインフォームのパス
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                //SpringSecurityのログアウトをPOSTにしなければならないという
                // ログアウトを不正なユーザに行わせないために、ログアウト（/logout）はCSRFトークンのチェックを実施するようになっているため
                // ログアウトがパス(GET)の場合設定する（CSRF対応）ただおそらくあまり推奨されていない
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")  // ログアウト成功時の遷移先指定
                .permitAll()
                .deleteCookies("JSESSIONID") // ログアウト時にセッションIDをクッキーから削除する
                .and()
                .sessionManagement().invalidSessionUrl("/");  // 無効なセッションIDが指定された場合の遷移先を指定
    }

//    /**
//     * ユーザー情報を設定
//     * @param auth
//     * @throws Exception
//     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // DB使う場合
//        auth
//            .jdbcAuthentication()
//                .dataSource(dataSource)
//                .authoritiesByUsernameQuery("select user_id as username, role as authority from user_roles where user_id = ?")
//                .usersByUsernameQuery("select user_id as username, password, enabled from user_info where user_id = ?")
//        ;
//
////        auth
////            .jdbcAuthentication()
////                .dataSource(dataSource)
////                .withDefaultSchema()
////                .withUser("user").password("user").roles("USER").and()
////                .withUser("admin").password("admin").roles("ADMIN");
//
//        // DB使わない場合
////        auth
////            .inMemoryAuthentication()
////                .withUser("user").password("user").roles("USER")
////                .and()
////                .withUser("admin").password("admin").roles("ADMIN")
////                ;
//    }

    /**
     * 独自処理の呼び出し
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // ユーザー認証処理
                .userDetailsService(myUserDetailsService())
                // パスワード認証処理
                .passwordEncoder(new ShaPasswordEncoder(256))
        ;
    }

    @Bean
    public UserDetailsService myUserDetailsService() {
        return new MyUserDetailsService();
    }
}