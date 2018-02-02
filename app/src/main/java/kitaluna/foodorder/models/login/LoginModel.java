package kitaluna.foodorder.models.login;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kitaluna.foodorder.activitys.LoginActivity;
import kitaluna.foodorder.firebase.GoogleSignInOptionUtils;
import kitaluna.foodorder.utils.NetworkUtils;

/**
 * Created by Van Hien on 1/2/2018.
 */

public class LoginModel implements ILoginModel {

    /**
     * login with email and password
     * @param activity
     * @param accountLogin
     * @param modelListener
     */
    @Override
    public void onLoginEmailAndPassword(Activity activity, final AccountLogin accountLogin, final ILoginModelListener modelListener) {
        if (NetworkUtils.isNetworkAvailable(activity)){
            final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(accountLogin.getEmail(),accountLogin.getPassword())
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                modelListener.onLoginSuccess(user);
                            } else {
                                modelListener.onLoginFailed("Please check your account again!");
                            }
                        }
                    });
        } else {
            modelListener.onLoginFailed("No Internet");
        }

    }

    /**
     * login with google account
     * @param activity
     * @param modelListener
     */
    @Override
    public void onLoginGoogle(Activity activity, final ILoginModelListener modelListener) {
        if (NetworkUtils.isNetworkAvailable(activity)){
            GoogleSignInClient mGoogleSignInClient;
            GoogleApiClient googleApiClient;
            GoogleSignInOptions googleSignInOptions= GoogleSignInOptionUtils.getGoogleSignInOptions(activity);

            mGoogleSignInClient = GoogleSignIn.getClient(activity,googleSignInOptions);

            googleApiClient = new GoogleApiClient.Builder(activity)
                    .enableAutoManage((FragmentActivity) activity, new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            modelListener.onLoginFailed("No Internet");
                        }

                    })

                    .build();
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            activity.startActivityForResult(signInIntent, LoginActivity.REQUEST_CODE_GOOGLE);

        } else {
            modelListener.onLoginFailed("No Internet! Please check your connect");
        }
    }

    @Override
    public void onDismissLoginGoogle(Activity activity) {

    }


}
