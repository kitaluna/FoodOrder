package kitaluna.foodorder.fragments;

import kitaluna.foodorder.models.login.AccountLogin;

/**
 * Created by Van Hien on 12/31/2017.
 */

public interface IRegisterFragmentResponse {
    void onSignUp(AccountLogin accountRegister);
    void onBack();
}
