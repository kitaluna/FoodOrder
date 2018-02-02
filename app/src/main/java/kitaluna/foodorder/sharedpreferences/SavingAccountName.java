package kitaluna.foodorder.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import kitaluna.foodorder.R;
/**
 * Created by Van Hien on 1/31/2018.
 */

public class SavingAccountName {

    public static void saveAccountName(Context context,String accountName){
        SharedPreferences shared;

        shared = context.getSharedPreferences(context.getString(R.string.account_names), Context.MODE_PRIVATE);

        String accountString = shared.getString(context.getString(R.string.account_string),"");

        accountString += accountName+",";

        SharedPreferences.Editor editor = shared.edit();

        editor.putString(context.getString(R.string.account_string),accountString);
        editor.apply();

    }

    public static String[] getAccountNames(Context context){

        SharedPreferences shared;

        shared = context.getSharedPreferences(context.getString(R.string.account_names), Context.MODE_PRIVATE);

        String accountString = shared.getString(context.getString(R.string.account_string),"");

        String arr[] = accountString.split(",");
        return arr;
    }

}
