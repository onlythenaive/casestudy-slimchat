package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.xml.bind.DatatypeConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User profile.
 *
 * @author Ilia Gubarev
 */
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
    private Boolean restricted;
    private Boolean connected;
    private Boolean ownedByPrincipal;
    private String status;

    public String getLastSpottedAtPretty() {
        if (lastSpottedAt == null) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return this.lastSpottedAt.atOffset(ZoneOffset.ofHours(3)).format(dateTimeFormatter);
    }

    public boolean isOnline() {
        return this.lastSpottedAt != null && this.lastSpottedAt.plusSeconds(60).isAfter(Instant.now());
    }

    public boolean isViewable() {
        return ownedByPrincipal || !restricted || connected;
    }

    public String getDisplayName() {
        if (this.firstname != null && this.lastname != null) {
            return this.firstname + " " + this.lastname;
        }
        return getShortDisplayName();
    }

    public String getShortDisplayName() {
        return this.firstname != null ? this.firstname : this.accountName;
    }

    public String getGravatarHash() {
        return this.email != null ? md5(this.email) : null;
    }

    private String md5(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
