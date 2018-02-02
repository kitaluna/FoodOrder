package kitaluna.foodorder.models.login;

import android.app.Activity;

/**
 * Created by Van Hien on 1/2/2018.
 */

public interface ILoginModel {
    void onLoginEmailAndPassword(Activity activity,AccountLogin accountLogin, ILoginModelListener modelListener);
    void onLoginGoogle(Activity activity,ILoginModelListener modelListener);
    void onDismissLoginGoogle(Activity activity);
}
