package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainAccessorBean;

/**
 * Connection proposal accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProposalAccessorBean extends DomainAccessorBean<ProposalEntity> implements ProposalAccessor {

    @Override
    public ProposalEntity ensureAccess(AccessLevel level, ProposalEntity subject) {
        if (subject.getInitiatorId().equals(principalId()) || subject.getAcceptorId().equals(principalId())) {
            return subject;
        }
        throw insufficientPrivileges();
    }

    @Override
    protected String entityName() {
        return "proposal";
    }
}