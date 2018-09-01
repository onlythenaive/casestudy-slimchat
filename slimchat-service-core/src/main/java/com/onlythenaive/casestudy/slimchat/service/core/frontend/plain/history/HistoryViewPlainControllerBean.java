package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryFacade;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/history")
public class HistoryViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @PostMapping("/delete")
    public ModelAndView delete(HistoryFormInput form) {
        if (form.getGroupId() != null) {
            this.historyFacade.deleteByGroupId(form.getGroupId());
            return redirect("chats/group/" + form.getGroupId());
        } else {
            this.historyFacade.deleteByProfileId(form.getProfileId());
            return redirect("chats/" + form.getProfileId());
        }
    }
}
