package kitaluna.foodorder.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

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
public class RegisterFragment extends Fragment {


    @BindView(R.id.edt_register_email)
    AppCompatEditText edtRegisterEmail;
    @BindView(R.id.edt_register_pass)
    AppCompatEditText edtRegisterPass;
    @BindView(R.id.btn_register_sign_up)
    Button btnRegisterSignUp;
    @BindView(R.id.edt_register_pass_confirm)
    AppCompatEditText edtRegisterPassConfirm;
    @BindView(R.id.img_register_back)
    ImageButton imgRegisterBack;

    Unbinder unbinder;

    private IRegisterFragmentResponse fragmentResponse;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IRegisterFragmentResponse) {
            fragmentResponse = (IRegisterFragmentResponse) context;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        unbinder = ButterKnife.bind(this, view);
        initEvents(view);
        return view;
    }

    private void initEvents(View view) {


    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (fragmentResponse != null) {
            fragmentResponse = null;
        }
    }


    private void handleSignUp(View view) {
        String password = edtRegisterPass.getText().toString().trim();
        String passConfirm = edtRegisterPassConfirm.getText().toString().trim();
        String email = edtRegisterEmail.getText().toString().trim();
        boolean emailOK = true;
        boolean passwordOK = true;
        boolean passConfirmOk=true;
        if (email.length() == 0) {
            edtRegisterEmail.setError("This field is required.");
            emailOK = false;
        } else {
            if (!StringUtils.checkEmail(email)) {
                edtRegisterEmail.setError("Hmmm, that’s not a valid email address.");
                emailOK = false;
            }
        }

        if (password.length() == 0) {
            edtRegisterPass.setError("This field is required.");
            passwordOK = false;
        } else {
            if (password.length() < 8 && password.length() > 0) {
                edtRegisterPass.setError("Your password must have at least 8 characters!");
                passwordOK = false;
            }
        }

        if (passConfirm.length()==0){
            edtRegisterPassConfirm.setError("This field is required.");
            passConfirmOk=false;
        } else {
            if (!passConfirm.equals(password)){
                edtRegisterPassConfirm.setError("Passwords don’t match.");
                passConfirmOk=false;
            }
        }
        if (passwordOK && emailOK && passConfirmOk) {
            AccountLogin accountLogin = new AccountLogin(email, password);
            fragmentResponse.onSignUp(accountLogin);
        } else {

        }

    }


    private void handleBack(View view) {
        fragmentResponse.onBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_register_sign_up, R.id.img_register_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register_sign_up:
                handleSignUp(view);
                break;
            case R.id.img_register_back:
                handleBack(view);
                break;
        }
    }
}
