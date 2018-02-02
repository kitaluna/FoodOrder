package kitaluna.foodorder.firebase;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import kitaluna.foodorder.R;

/**
 * Created by Van Hien on 1/31/2018.
 */

public class GoogleSignInOptionUtils {
    private static GoogleSignInOptions googleSignInOptions = null;

    public static  GoogleSignInOptions getGoogleSignInOptions(Context context){
        if (googleSignInOptions == null) {
           googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getApplicationContext().getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
        }
        return googleSignInOptions;
    }
}
