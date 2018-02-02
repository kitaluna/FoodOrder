package kitaluna.foodorder.presenters.register;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

import kitaluna.foodorder.models.login.AccountLogin;
import kitaluna.foodorder.models.register.IRegisterModelListener;
import kitaluna.foodorder.models.register.RegisterModel;
import kitaluna.foodorder.views.register.IRegisterView;

/**
 * Created by Van Hien on 1/1/2018.
 */

public class RegisterPresenter implements IRegisterPresenter, IRegisterModelListener {
    private RegisterModel registerModel;
    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        registerModel = new RegisterModel();
    }

    @Override
    public void onSignUp(Activity activity,AccountLogin accountRegister) {
        iRegisterView.showProgress();
        registerModel.onSignUp(activity,accountRegister,this);
    }

    /*Response from LoginModel */


    @Override
    public void onSignUpFailed(String message) {
        iRegisterView.hideProgress();
        iRegisterView.onSignUpFailed(message);
    }

    @Override
    public void onSignUpSuccess(FirebaseUser user) {
        iRegisterView.hideProgress();
        iRegisterView.onSignUpSuccess(user);
    }
    
}
