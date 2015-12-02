package app.controller.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.form.SignupForm;
import app.service.SignupService;

/**
 * Created by s-wada on 2015/11/25.
 */


@Controller
@EnableAutoConfiguration
@RequestMapping("/signup")
public class SignUpController {
    private static final Logger log = LoggerFactory.getLogger(SignUpController.class);

    @ModelAttribute
    SignupForm setupForm() {
        return new SignupForm();
    }

    @Autowired
    SignupService signupService;

    @RequestMapping(method = RequestMethod.GET)
    public String visitSinup() {
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signup(@Validated SignupForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
        } else {
            signupService.signup(form);
        }
        return "signup";
    }
}
