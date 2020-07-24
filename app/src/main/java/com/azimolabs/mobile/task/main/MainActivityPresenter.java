package com.azimolabs.mobile.task.main;

import com.azimolabs.mobile.task.base.BasePresenter;
import com.azimolabs.mobile.task.utils.Navigator;

import javax.inject.Inject;

import static com.azimolabs.mobile.task.utils.ErrorType.EMPTY_FIELD;

class MainActivityPresenter extends BasePresenter {
    private final MainActivity view;
    private final Navigator navigator;
    private final UserFieldErrorDisposer userFieldErrorDisposer;

    @Inject
    public MainActivityPresenter(
            MainActivity view,
            Navigator navigator,
            UserFieldErrorDisposer userFieldErrorDisposer
    ) {
        this.view = view;
        this.navigator = navigator;
        this.userFieldErrorDisposer = userFieldErrorDisposer;
    }

    public void openReposListForUser(String name) {
        if (name.isEmpty()) {
            view.showError(userFieldErrorDisposer.getErrorText(EMPTY_FIELD));
        } else {
            navigator.navigateToUserDetailsActivity(name);
        }
        view.hideKeyboard();
    }

    public void textChanged() {
        view.hideError();
    }
}
