package kitaluna.foodorder.fragments;


import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kitaluna.foodorder.R;
import kitaluna.foodorder.models.login.AccountLogin;
import kitaluna.foodorder.utils.StringUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.chb_login_save_account)
    AppCompatCheckBox chbLoginSaveAccount;
    @BindView(R.id.edt_login_email)
    AppCompatEditText edtLoginEmail;
    @BindView(R.id.edt_login_pass)
    AppCompatEditText edtLoginPass;
    @BindView(R.id.btn_login_sign_in)
    Button btnLoginSignIn;
    @BindView(R.id.txt_login_sign_up)
    TextView txtLoginSignUp;

    @BindView(R.id.btn_login_google_sign_in)
    SignInButton btn_google_sign_in;

    Unbinder unbinder;

    private ILoginFragmentResponse fragmentResponse;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ILoginFragmentResponse) {
            fragmentResponse = (ILoginFragmentResponse) context;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        initEvents(view);

        return view;
    }

    private void initEvents(View view) {
        txtLoginSignUp.setPaintFlags(txtLoginSignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (fragmentResponse != null) {
            fragmentResponse = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @OnClick({R.id.btn_login_sign_in, R.id.txt_login_sign_up,R.id.btn_login_google_sign_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_sign_in:
                handleSignIn(view);
                break;
            case R.id.txt_login_sign_up:
                handleSignUp(view);
                break;
            case R.id.btn_login_google_sign_in:
                handleSignInGoogle(view);
                break;
        }
    }

    private void handleSignInGoogle(View view) {
        if (fragmentResponse!=null){
            fragmentResponse.onSignInGoogle();
        }
    }

    private void handleSignUp(View view) {
        if (fragmentResponse != null) {
            fragmentResponse.onSignUpFragment();
        }
    }

    private void handleSignIn(View view) {
        String email = edtLoginEmail.getText().toString().trim();
        String password = edtLoginPass.getText().toString().trim();
        AccountLogin account = new AccountLogin(email, password);
        if (checkAccount(account)) {
            fragmentResponse.onSignInEmailAndPassword(account);
            if (chbLoginSaveAccount.isChecked()){
                fragmentResponse.onSaveAccount(account);
            }

        } else {
            Toast.makeText(getActivity(), "Please check your account again!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkAccount(AccountLogin accountLogin){

        boolean emailOK = true;
        boolean passwordOK = true;

        if (accountLogin.getEmail().length() == 0) {
            edtLoginEmail.setError("This field is required.");
            emailOK = false;
        } else {
            if (!StringUtils.checkEmail(accountLogin.getEmail())) {
                edtLoginEmail.setError("Hmmm, that’s not a valid email address.");
                emailOK = false;
            }
        }

        if (accountLogin.getPassword().length() == 0) {
            edtLoginPass.setError("This field is required.");
            passwordOK = false;
        } else {
            if (accountLogin.getPassword().length() < 8 && accountLogin.getPassword().length() > 0) {
                edtLoginPass.setError("Your password must have at least 8 characters!");
                passwordOK = false;
            }
        }
        if (passwordOK && emailOK){
            return  true;
        } else {
            return false;
        }
    }
}
