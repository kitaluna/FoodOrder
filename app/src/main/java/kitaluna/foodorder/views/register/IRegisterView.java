package kitaluna.foodorder.views.register;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Van Hien on 1/2/2018.
 */

public interface IRegisterView {
    void onSignUpSuccess(FirebaseUser user);
    void onSignUpFailed(String message);
    void showProgress();
    void hideProgress();
}
