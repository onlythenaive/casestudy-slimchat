package com.onlythenaive.casestudy.slimchat.service.core.view.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileService;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.Authentication;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContext;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/profile")
public class ProfileViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private AuthenticationContext authenticationContext;

    @GetMapping("/me")
    public ModelAndView getCurrent() {
        Authentication authentication = this.authenticationContext.getAuthentication();
        if (authentication == null) {
            throw OperationException.builder()
                    .category(ExceptionCategory.SECURITY)
                    .comment("Not authenticated")
                    .textcode("x.security.not-authenticated")
                    .build();
        }
        String nickname = authentication.getAccount().getNickname();
        Profile profile = this.profileService.findProfileByNickname(nickname);
        return defaultView(profile);
    }

    @GetMapping("/{nickname}")
    public ModelAndView getByNickname(@PathVariable("nickname") String nickname) {
        Profile profile = this.profileService.findProfileByNickname(nickname);
        return defaultView(profile);
    }

    @GetMapping("/id-{id}")
    public ModelAndView getById(@PathVariable("id") String id) {
        Profile profile = this.profileService.getProfileById(id);
        return defaultView(profile);
    }

    @PostMapping("/id-{id}")
    public ModelAndView post(@PathVariable("id") String id, Profile profile) {
        Profile updatedProfile = this.profileService.updateProfile(id, profile);
        return defaultView(updatedProfile);
    }

    @Override
    protected String defaultViewName() {
        return "profile";
    }
}