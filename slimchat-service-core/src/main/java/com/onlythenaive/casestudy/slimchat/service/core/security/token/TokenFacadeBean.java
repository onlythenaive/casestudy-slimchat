package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.utility.hash.BcryptHashService;

/**
 * Security token operations facade implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenFacadeBean extends GenericComponentBean implements TokenFacade {

    @Autowired
    private AccountProvider accountProvider;

    @Autowired
    private BcryptHashService hashService;

    @Autowired
    private TokenContextConfigurator tokenContextConfigurator;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public void createFromAccountLoginPair(String loginKey, String loginSecret) {
        Account account = this.accountProvider.findByLoginKey(loginKey).orElseThrow(() -> loginFailed(loginKey));
        boolean verified = this.hashService.verify(loginSecret, account.getLoginSecretHash());
        if (!verified) {
            throw loginFailed(loginKey);
        }
        this.tokenGenerator.generateForAccountId(account.getId());
    }

    @Override
    public void delete() {
        this.tokenContextConfigurator.getProvided().ifPresent(token -> {
            this.tokenRepository.deleteById(token.getId());
            this.tokenContextConfigurator.setProvided(null);
            logger().debug("Deleted a security token with ID = {}", token.getId());
        });
    }

    private OperationException loginFailed(String loginKey) {
        return OperationException.builder()
                .category(ExceptionCategory.SECURITY)
                .comment("Login attempt failed")
                .textcode("x.security.authentication.login-failed")
                .dataAttribute("loginKey", loginKey)
                .build();
    }
}
