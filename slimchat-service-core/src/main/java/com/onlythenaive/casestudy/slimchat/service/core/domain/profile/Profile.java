package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private String id;

    private String accountName;
    private String email;
    private String firstname;
    private String lastname;
    private Instant lastSpottedAt;
    private Instant lastUpdatedAt;
    private Instant registeredAt;
    private Boolean own;
    private String status;
}
