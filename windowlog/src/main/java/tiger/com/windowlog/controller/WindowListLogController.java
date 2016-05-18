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
    WindowListLogView mLogView;
    private static WindowListLogController mWindowListLogController;

    private WindowListLogController() {
    }

    public static WindowListLogController getInstance() {
        if (mWindowListLogController == null) {
            mWindowListLogController = new WindowListLogController();
        }
        return mWindowListLogController;
    }

    public void init(Context context) {
        mLogView = new WindowListLogView(context);
    }

    /**
     * 显示log
     * @param log log
     */
    public void log(String log) {
        WindowLogRepertory.getInstance().updateLog(log);
    }

    /**
     * 显示悬浮窗
     */
    public void showWindowLog() {
        mLogView.showView();
        registerLogView();
    }

    public void closeWindowLog() {
        mLogView.closeView();
        unregisterLogView();
    }

    public void unregisterLogView() {
        WindowLogRepertory.getInstance().unregisterObserver(mLogView);
    }

    public void registerLogView() {
        if (!WindowLogRepertory.getInstance().hasRegister(mLogView)) {
            WindowLogRepertory.getInstance().registerObserver(mLogView);
        }
    }

}
