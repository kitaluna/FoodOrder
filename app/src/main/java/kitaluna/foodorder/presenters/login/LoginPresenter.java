package kitaluna.foodorder.presenters.login;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

import kitaluna.foodorder.models.login.AccountLogin;
import kitaluna.foodorder.models.login.ILoginModelListener;
import kitaluna.foodorder.models.login.LoginModel;
import kitaluna.foodorder.views.login.ILoginView;

/**
 * Created by Van Hien on 1/1/2018.
 */

public class LoginPresenter implements ILoginPresenter, ILoginModelListener {
    private final String TAG=getClass().getSimpleName();
    private LoginModel loginModel;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        loginModel = new LoginModel();
    }

    @Override
    public void onSignInEmailAndPassword(Activity activity,AccountLogin accountLogin) {
        iLoginView.showProgress();
        loginModel.onLoginEmailAndPassword(activity,accountLogin,this);
    }

    @Override
    public void onSignInGoogle(Activity activity){
        iLoginView.showProgress();
        loginModel.onLoginGoogle(activity,this);
    }

    @Override
    public void onDismissLoginGoogle(Activity activity) {
        loginModel.onDismissLoginGoogle(activity);
    }
    /*Response from LoginModel */


    @Override
    public void onLoginFailed(String message) {
        iLoginView.hideProgress();
        iLoginView.onLoginFailed(message);
    }

    @Override
    public void onLoginSuccess(FirebaseUser user) {
        iLoginView.hideProgress();
        iLoginView.onLoginSuccess(user);
    }
}
