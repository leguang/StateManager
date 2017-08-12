package cn.itsite.demo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class ToastUtils {
    private static final String TAG = ToastUtils.class.getSimpleName();

    public static Toast mToast;

    public static void showToast(Context mContext, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
