package kitaluna.foodorder.models.register;

import android.app.Activity;

import kitaluna.foodorder.models.login.AccountLogin;

/**
 * Created by Van Hien on 1/2/2018.
 */

public interface IRegisterModel {
    void onSignUp(Activity activity, AccountLogin accountRegister, IRegisterModelListener modelListener);
}
