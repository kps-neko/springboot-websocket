package app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * コントローラクラスを介さずにログイン画面を表示させるため、ViewController
 *
 * @author a-numadate
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("top");
      registry.addViewController("/input").setViewName("input");
      registry.addViewController("/login").setViewName("login");
      registry.addViewController("/user/sample").setViewName("user/userSample");
//       registry.addViewController("/admin/sample").setViewName("admin/adminSample");
   }
}
