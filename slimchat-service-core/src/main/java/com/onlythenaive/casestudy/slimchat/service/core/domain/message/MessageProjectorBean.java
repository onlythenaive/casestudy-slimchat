package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfilePreviewProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Chat message projector implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class MessageProjectorBean extends GenericComponentBean implements MessageProjector {

    @Autowired
    private GroupProvider groupProvider;

    @Autowired
    private ProfilePreviewProvider profilePreviewProvider;

    @Override
    public Message project(MessageEntity entity) {
        return Message.builder()
                .id(entity.getId())
                .text(entity.getText())
                .author(profilePreview(entity.getAuthorId()))
                .group(group(entity.getGroupId()))
                .recipient(profilePreview(entity.getRecipientId()))
                .own(entity.getAuthorId().equals(principalId()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private Group group(String groupId) {
        if (groupId == null) {
            return null;
        }
        return this.groupProvider.findPreviewById(groupId).orElse(null);
    }

    private Profile profilePreview(String profileId) {
        if (profileId == null) {
            return null;
        }
        return this.profilePreviewProvider.getById(profileId);
    }
}
