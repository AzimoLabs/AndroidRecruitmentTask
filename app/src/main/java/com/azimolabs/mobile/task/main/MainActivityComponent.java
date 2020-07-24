package com.azimolabs.mobile.task.main;

import com.azimolabs.mobile.task.base.BaseActivityModule;
import com.azimolabs.mobile.task.github.ActivityComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Subcomponent;

@Singleton
@Subcomponent(
        modules = MainActivityComponent.MainActivityModule.class
)
public interface MainActivityComponent extends ActivityComponent {
    MainActivity inject(MainActivity activity);

    @Module
    class MainActivityModule extends BaseActivityModule<MainActivity> {

        public MainActivityModule(MainActivity activity) {
            super(activity);
        }
    }
}