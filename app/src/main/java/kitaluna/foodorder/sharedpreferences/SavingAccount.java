package kitaluna.foodorder.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import kitaluna.foodorder.R;
import kitaluna.foodorder.models.login.AccountLogin;

/**
 * Created by Van Hien on 1/31/2018.
 */

public class SavingAccount {

    /**
     * save a account with email and password
     * @param accountLogin
     */
    public static void saveAccountToStorage(Context context, AccountLogin accountLogin){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.account_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.email_key), accountLogin.getEmail());
        editor.putString(context.getString(R.string.password_key), accountLogin.getEmail());
        editor.apply();
    }

    public static AccountLogin getAcountFromStorage(Context context){
        SharedPreferences sharedPref =context.getSharedPreferences(context.getString(R.string.account_file),Context.MODE_PRIVATE);
        String email = sharedPref.getString(context.getString(R.string.email_key),"");
        String pass = sharedPref.getString(context.getString(R.string.password_key),"");
        return new AccountLogin(email,pass);
    }

    public static boolean isSaveAccount(Context context){
        SharedPreferences sharedPref =context.getSharedPreferences(context.getString(R.string.account_file),Context.MODE_PRIVATE);
        return sharedPref.getBoolean(context.getString(R.string.isSave), false);
    }

    public static void saveStateSave(Context context, boolean isSave){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.account_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(context.getString(R.string.isSave), isSave);
        editor.apply();
    }

}
