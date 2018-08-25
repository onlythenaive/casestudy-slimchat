package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

/**
 * Chat history invoice.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryInvoice {

    private Profile referencedUser;
    private Group referencedGroup;
}