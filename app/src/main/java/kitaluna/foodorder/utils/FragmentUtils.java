package kitaluna.foodorder.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import kitaluna.foodorder.R;

/**
 * Created by Van Hien on 1/1/2018.
 */

public class FragmentUtils {
    public static void replaceFragment(FragmentManager manager, Fragment fragment) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_to_left);
        ft.replace(R.id.frame_login_container, fragment);
        ft.commitAllowingStateLoss();
        manager.executePendingTransactions();
    }
}
