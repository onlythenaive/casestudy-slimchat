package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;

/**
 * Profile operations facade.
 *
 * @author Ilia Gubarev
 */
public interface ProfileFacade {

    /**
     * Finds all existing profiles.
     *
     * @return a collection of existing profiles.
     */
    Collection<Profile> find();

    /**
     * Retrieves an existing profile by the name of its referenced account.
     *
     * @param accountName the name of a referenced account.
     * @return the requested profile.
     */
    Profile getByAccountName(String accountName);

    /**
     * Retrieves an existing profile preview by the name of its referenced account.
     *
     * @param accountName the name of a referenced account.
     * @return the requested profile preview.
     */
    Profile getPreviewByAccountName(String accountName);

    /**
     * Retrieves an existing profile by its ID (matches with its referenced account ID).
     *
     * @param id the ID of a profile.
     * @return the requested profile.
     */
    Profile getById(String id);

    /**
     * Retrieves an existing profile preview by its ID (matches with its referenced account ID).
     *
     * @param id the ID of a profile.
     * @return the requested profile preview.
     */
    Profile getPreviewById(String id);

    /**
     * Updates properties of an existing profile by its ID.
     *
     * @param id the ID of a profile.
     * @param invoice update properties to be applied.
     * @return the updated profile.
     */
    Profile update(String id, ProfileUpdateInvoice invoice);
}
