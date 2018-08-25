package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Optional;

/**
 * Chat group provider.
 *
 * @author Ilia Gubarev
 */
public interface GroupProvider {

    /**
     * Provides an existing chat group by its ID.
     *
     * @param id the ID of an existing group.
     * @return the requested group.
     */
    Group getById(String id);

    /**
     * Provides an existing chat group by its ID if any.
     *
     * @param id the ID of an existing group.
     * @return the requested group.
     */
    Optional<Group> findPreviewById(String id);
}