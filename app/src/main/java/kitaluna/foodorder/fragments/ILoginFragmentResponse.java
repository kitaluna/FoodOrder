package kitaluna.foodorder.fragments;

import kitaluna.foodorder.models.login.AccountLogin;

/**
 * Created by Van Hien on 12/31/2017.
 */

public interface ILoginFragmentResponse {
    void onSignInEmailAndPassword(AccountLogin account);
    void onSignInGoogle();
    void onSignUpFragment();
    void onSaveAccount(AccountLogin accountLogin);
    void onSaveAccountName(String accountName);
}
