package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainAccessorBean;

/**
 * User profile accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileAccessorBean extends GenericDomainAccessorBean<ProfileEntity> implements ProfileAccessor {

    @Override
    public ProfileEntity ensureAccess(AccessLevel level, ProfileEntity subject) {
        switch (level) {
            case PREVIEW:
                return subject;
            case VIEW:
                return ensureAccessForView(subject);
            case EDIT:
                return ensureAccessForUpdate(subject);
            case MODERATE:
            default:
                throw notSupported();
        }
    }

    @Override
    protected String entityName() {
        return "profile";
    }

    private ProfileEntity ensureAccessForView(ProfileEntity entity) {
        if (ownedByPrincipal(entity)) {
            return entity;
        }
        if (entity.getRestricted() && !connectedWithPrincipal(entity)) {
            throw insufficientPrivileges();
        }
        return entity;
    }

    private ProfileEntity ensureAccessForUpdate(ProfileEntity entity) {
        if (!ownedByPrincipal(entity)) {
            throw insufficientPrivileges();
        }
        return entity;
    }

    private boolean connectedWithPrincipal(ProfileEntity entity) {
        return entity.getConnectedUserIds().contains(authenticated().getId());
    }

    private boolean ownedByPrincipal(ProfileEntity entity) {
        return authenticated().getId().equals(entity.getId());
    }
}
