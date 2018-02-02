package kitaluna.foodorder.presenters.login;

import android.app.Activity;

import kitaluna.foodorder.models.login.AccountLogin;

/**
 * Created by Van Hien on 1/1/2018.
 */

public interface ILoginPresenter {
    void onSignInEmailAndPassword(Activity activity,AccountLogin accountLogin);
    void onSignInGoogle(Activity activity);
    void onDismissLoginGoogle(Activity activity);
}
