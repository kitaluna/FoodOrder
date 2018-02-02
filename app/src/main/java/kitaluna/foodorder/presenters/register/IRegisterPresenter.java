package kitaluna.foodorder.presenters.register;

import android.app.Activity;

import kitaluna.foodorder.models.login.AccountLogin;

/**
 * Created by Van Hien on 1/1/2018.
 */

public interface IRegisterPresenter {
    void onSignUp(Activity activity,AccountLogin accountRegister);
}
