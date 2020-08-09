package com.azimolabs.mobile.task.di;

import com.azimolabs.mobile.task.base.BaseActivityModule;
import com.azimolabs.mobile.task.github.ActivityComponent;
import com.azimolabs.mobile.task.view.UserProfileActivity;

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