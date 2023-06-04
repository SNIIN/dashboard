package cn.edu.xmu.wlw.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.xmu.wlw.R;

public class ToastUtils {
    public static void showMyToast(Context context, String message, int lengthShort) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.my_toast, null);

        TextView text = (TextView) layout.findViewById(R.id.my_toast_text);
        text.setText(message);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, 50);
        toast.setDuration(lengthShort);
        toast.setView(layout);
        toast.show();
    }
}