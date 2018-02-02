package kitaluna.foodorder.models.register;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kitaluna.foodorder.models.login.AccountLogin;
import kitaluna.foodorder.utils.NetworkUtils;

/**
 * Created by Van Hien on 1/2/2018.
 */

public class RegisterModel implements IRegisterModel {

    @Override
    public void onSignUp(Activity activity, AccountLogin accountRegister, final IRegisterModelListener modelListener) {
        if (NetworkUtils.isNetworkAvailable(activity)){
            final FirebaseAuth mAuth=FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(accountRegister.getEmail(), accountRegister.getPassword())
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                modelListener.onSignUpSuccess(user);
                            } else {
                                modelListener.onSignUpFailed("Email was existed");
                            }

                            // ...
                        }
                    });
        } else {
            modelListener.onSignUpFailed("No Internet!");
        }



    }
}
