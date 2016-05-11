package tiger.com.windowlog.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by yinghu.gyh on 2016/5/11.
 *
 * @version 1.0
 */
public class WindowViewFactory {
    private static final int BACKGROUND_COLOR = 0x80000000;
    public static ListView buildWindowListView(Context context, BaseAdapter adapter) {
        ListView listView = new ListView(context);
        listView.setBackgroundColor(BACKGROUND_COLOR);
        listView.setPadding(20, 0, 150, 0);
        listView.setAdapter(adapter);
        return listView;
    }

    public static WindowManager.LayoutParams buildWindowManagerParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.gravity = Gravity.LEFT;
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
//                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        return params;
    }

}
