package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    private String id;

    private String caption;
    private Boolean personal;
    private Collection<Profile> admins;
    private Collection<Profile> participants;
    private Collection<Message> latestMessages;
}
