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
       registry.addViewController("/").setViewName("home");
   }
}
