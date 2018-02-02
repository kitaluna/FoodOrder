package kitaluna.foodorder.models.register;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Van Hien on 1/2/2018.
 */

public interface IRegisterModelListener {
    void onSignUpFailed(String message);
    void onSignUpSuccess(FirebaseUser user);
}
