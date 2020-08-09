package com.azimolabs.mobile.task.main;

import com.azimolabs.mobile.task.base.BaseActivityModule;
import com.azimolabs.mobile.task.github.ActivityComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Subcomponent;

@Singleton
@Subcomponent(
        modules = UserProfileActivityComponent.UserProfileActivityModule.class
)
public interface UserProfileActivityComponent extends ActivityComponent {
    UserProfileActivity inject(UserProfileActivity activity);

    @Module
    class UserProfileActivityModule extends BaseActivityModule<UserProfileActivity> {

        public UserProfileActivityModule(UserProfileActivity activity) {
            super(activity);
        }
    }
}