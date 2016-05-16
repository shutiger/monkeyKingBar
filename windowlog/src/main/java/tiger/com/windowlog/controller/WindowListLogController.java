package tiger.com.windowlog.controller;

import android.content.Context;

import tiger.com.windowlog.model.WindowLogRepertory;
import tiger.com.windowlog.view.WindowListLogView;

/**
 * Created by yinghu.gyh on 2016/5/13.
 *
 * @version 1.0
 */
public class WindowListLogController {
    public WindowListLogController(Context context) {
        WindowListLogView mLogView = new WindowListLogView(context);
        mLogView.showView();
        WindowLogRepertory.getInstance().registerObserver(mLogView);
    }

    public void log(String log) {
        WindowLogRepertory.getInstance().updateLog(log);
    }

}
