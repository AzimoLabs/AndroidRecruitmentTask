package com.azimolabs.mobile.task.main;

import com.azimolabs.mobile.task.utils.ErrorType;
import com.azimolabs.mobile.task.utils.Navigator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainActivityPresenterTest {
    @Mock
    MainActivity view;
    @Mock
    Navigator navigator;
    @Mock
    UserFieldErrorDisposer userFieldErrorDisposer;

    MainActivityPresenter tested;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        tested = new MainActivityPresenter(
                view,
                navigator,
                userFieldErrorDisposer
        );
    }

    @Test
    public void testOpenReposListForUser_whenNameIsEmpty_shouldShowEmptyFieldErrorAndHideKeyboard() {
        UserFieldError userFieldErrorMock = new UserFieldError(ErrorType.EMPTY_FIELD, "errorMassage");
        when(userFieldErrorDisposer.getErrorText(ErrorType.EMPTY_FIELD)).thenReturn(userFieldErrorMock);

        tested.openReposListForUser("");

        verify(view).showError(userFieldErrorMock);
        verify(view).hideKeyboard();
    }

    @Test
    public void testTextChanged() {
        tested.textChanged();

        verify(view).hideError();
    }
}