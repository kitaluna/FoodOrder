package kitaluna.foodorder.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import kitaluna.foodorder.R;
import kitaluna.foodorder.fragments.ILoginFragmentResponse;
import kitaluna.foodorder.fragments.IRegisterFragmentResponse;
import kitaluna.foodorder.fragments.LoginFragment;
import kitaluna.foodorder.fragments.RegisterFragment;
import kitaluna.foodorder.models.login.AccountLogin;
import kitaluna.foodorder.presenters.login.LoginPresenter;
import kitaluna.foodorder.presenters.register.RegisterPresenter;
import kitaluna.foodorder.sharedpreferences.SavingAccount;
import kitaluna.foodorder.utils.FragmentUtils;
import kitaluna.foodorder.views.login.ILoginView;
import kitaluna.foodorder.views.register.IRegisterView;

public class LoginActivity extends AppCompatActivity implements IRegisterFragmentResponse, ILoginFragmentResponse, ILoginView, IRegisterView {
    public static final int REQUEST_CODE_GOOGLE = 111;
    private final String TAG = getClass().getSimpleName();
    private LoginPresenter loginPresenter;
    private RegisterPresenter registerPresenter;
    private FirebaseAuth.AuthStateListener authStateListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser() != null) {
                    updateUI(mAuth.getCurrentUser());
                }
            }
        };

        initEvents();
        FragmentUtils.replaceFragment(getSupportFragmentManager(), new LoginFragment());
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(authStateListener);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_GOOGLE:
                onGoogleSignInResult(resultCode, data);
                break;
        }

    }

    private void onGoogleSignInResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        } else {
            Toast.makeText(this, "Something wrong! Try later", Toast.LENGTH_SHORT).show();
        }

    }


    private void initEvents() {
        progressDialog = new ProgressDialog(this);
        loginPresenter = new LoginPresenter(this);
        registerPresenter = new RegisterPresenter(this);

    }

    @Override
    public void onSignUp(AccountLogin accountRegister) {
        registerPresenter.onSignUp(this, accountRegister);
    }

    @Override
    public void onBack() {
        FragmentUtils.replaceFragment(getSupportFragmentManager(), new LoginFragment());
    }

    @Override
    public void onSignInEmailAndPassword(AccountLogin account) {
        loginPresenter.onSignInEmailAndPassword(this, account);
    }

    @Override
    public void onSignInGoogle() {
        Log.i(TAG, "onSignInGoogle");
        loginPresenter.onSignInGoogle(this);
    }

    @Override
    public void onSignUpFragment() {
        FragmentUtils.replaceFragment(getSupportFragmentManager(), new RegisterFragment());
    }

    @Override
    public void onSaveAccount(AccountLogin accountLogin) {
        SavingAccount.saveAccountToStorage(this, accountLogin);
    }

    @Override
    public void onSaveAccountName(String accountName) {

    }


    @Override
    public void onLoginSuccess(FirebaseUser user) {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess(FirebaseUser user) {
        onBack();
        Toast.makeText(this, "Sign Up successed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseAuth mAuth = FirebaseAuth.getInstance();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {
                            updateUI(null);
                        }

                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
