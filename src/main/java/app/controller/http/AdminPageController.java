package app.controller.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import app.security.MyUserDetails;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    private static final Logger log = LoggerFactory.getLogger(AdminPageController.class);

    @Autowired
    private MyBean myBean;

    @RequestMapping("/sample")
    public String adminSample(Principal principal, Model model) {

        Authentication auth = (Authentication) principal;
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();

        log.info(userDetails.getUsername() + ":" + userDetails.getAuthorities() + ":" + userDetails.getOrgName());

        return "admin/adminSample";
    }
}


@Component
class MyBean {

    @Autowired
    public MyBean(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        List<String> files = args.getNonOptionArgs();
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
    }

}