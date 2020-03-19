package com.company.sample.core;

import com.haulmont.addon.bproc.provider.UserProvider;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("sample_TestUserProvider")
public class TestUserProvider implements UserProvider {

    @Inject
    private DataManager dataManager;

    @Override
    public User get(String executionId) {
        return dataManager.load(User.class)
                .query("where e.login = :login")
                .parameter("login", "admin")
                .one();
    }
}
