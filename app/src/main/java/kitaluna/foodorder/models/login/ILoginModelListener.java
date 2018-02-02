package kitaluna.foodorder.models.login;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Van Hien on 1/2/2018.
 */

public interface ILoginModelListener {
    void onLoginFailed(String message);
    void onLoginSuccess(FirebaseUser user);
}
