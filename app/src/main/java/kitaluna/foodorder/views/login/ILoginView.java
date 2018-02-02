package kitaluna.foodorder.views.login;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Van Hien on 1/2/2018.
 */

public interface ILoginView {
    void onLoginSuccess(FirebaseUser user);
    void onLoginFailed(String message);
    void showProgress();
    void hideProgress();

}
