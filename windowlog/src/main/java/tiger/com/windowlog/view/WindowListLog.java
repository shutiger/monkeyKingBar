package tiger.com.windowlog.view;

import android.content.Context;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import tiger.com.windowlog.adapter.LogItemAdapter;


/**
 * Created by yinghu.gyh on 2016/5/11.
 *
 * @version 1.0
 */
public class WindowListLog {
    private static final int TEXT_COLOR = 0xffffffff;
    private static final int TEXT_SHADOW = 0xff000000;
    private static final float TEXT_SIZE = 13.0f;
    private static final float SHADOW_RADIUS = 2.0f;
    private Context mContext;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLogTextParams;
    private boolean mTestMode = true;
    private ListView mListView;
    private static WindowListLog mWindowLogInstance = new WindowListLog();
    private LogItemAdapter mLogAdapter;
    private List<String> logs = new ArrayList<>();

    private WindowListLog(){}

    public static WindowListLog getInstance() {
        if (mWindowLogInstance == null) {
            mWindowLogInstance = new WindowListLog();
        }
        return mWindowLogInstance;
    }

    public void init(Context context) {
        mContext = context;
    }

    public void showView() {
        if (mTestMode && mListView == null) {
            mLogAdapter = new LogItemAdapter(mContext);
            mListView = WindowViewFactory.buildWindowListView(mContext, mLogAdapter);
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            mLogTextParams = WindowViewFactory.buildWindowManagerParams();
            mWindowManager.addView(mListView, mLogTextParams);
        }
    }

    public void log(String log) {
        logs.clear();
        logs.add(log);
        mLogAdapter.setLogs(logs);
    }
}
