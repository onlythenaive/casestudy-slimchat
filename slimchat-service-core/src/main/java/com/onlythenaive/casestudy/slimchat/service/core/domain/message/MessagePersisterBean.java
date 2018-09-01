package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericPersisterBean;

/**
 * Chat message persister implementation.
 *
 * @author Ili Gubarev
 */
@Service
public class MessagePersisterBean extends GenericPersisterBean<MessageEntity> implements MessagePersister {

    @Override
    public void insert(MessageEntity entity) {
        entity.setChatDescriptor(MessageChatDescriptorBuilder.of(entity).build());
        super.insert(entity);
    }
}
