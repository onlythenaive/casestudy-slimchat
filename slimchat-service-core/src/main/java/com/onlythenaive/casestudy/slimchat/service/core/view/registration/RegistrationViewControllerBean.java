package com.onlythenaive.casestudy.slimchat.service.core.view.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.SecurityService;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/registration")
public class RegistrationViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private SecurityService securityService;

    @GetMapping
    public ModelAndView get() {
        return defaultView();
    }

    @PostMapping
    public ModelAndView post(RegistrationFormData formData) {
        ensurePasswordDuplicate(formData);
        this.securityService.createAccount(formData.getNickname(), formData.getPassword());
        return view("registration-complete");
    }

    @Override
    protected String defaultViewName() {
        return "registration";
    }

    private void ensurePasswordDuplicate(RegistrationFormData formData) {
        if (!formData.getPassword().equals(formData.getPasswordDuplicate())) {
            RegistrationFormData data = RegistrationFormData.builder()
                    .nickname(formData.getNickname())
                    .build();
            throw OperationException.builder()
                    .category(ExceptionCategory.LOGIC)
                    .comment("Password does not match with its duplicate")
                    .data(data)
                    .textcode("x.logic.account.creation.password-duplicate-mismatch")
                    .build();
        }
    }
}
