package tiger.com.windowlog.view;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import tiger.com.windowlog.model.IDataObserver;
import tiger.com.windowlog.view.adapter.LogItemAdapter;


/**
 * Created by yinghu.gyh on 2016/5/11.
 *
 * @version 1.0
 */
public class WindowListLogView implements IWindowListLogView, IDataObserver {
    private Context mContext;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLogListParams;
    private boolean mTestMode = true;
    private boolean isLogViewShow = false;
    private ListView mListView;
    private LogItemAdapter mLogAdapter;
    private Button mHideButton;
    private WindowManager.LayoutParams mHideButtonParams;

    public WindowListLogView(Context context){
        mContext = context;
    }

    @Override
    public void showView() {
        if (mTestMode && mListView == null) {
            mLogAdapter = new LogItemAdapter(mContext);
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

            mListView = WindowViewFactory.buildWindowListView(mContext, mLogAdapter);
            mLogListParams = WindowViewFactory.buildWindowManagerParams();
            mWindowManager.addView(mListView, mLogListParams);

            mHideButton = WindowViewFactory.buildHideButton(mContext);
            mHideButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("隐".equals(mHideButton.getText())) {
                        mListView.setVisibility(View.GONE);
                        mHideButton.setText("显");
                    } else {
                        mListView.setVisibility(View.VISIBLE);
                        mHideButton.setText("隐");
                    }
                }
            });
            mHideButtonParams = WindowViewFactory.buildButtonLayoutParams();
            mWindowManager.addView(mHideButton, mHideButtonParams);

            isLogViewShow = true;
        }
    }

    @Override
    public void hideView() {
        if (mListView != null && mContext != null) {
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            wm.removeView(mListView);
            mListView = null;
            isLogViewShow = false;
        }
    }

    @Override
    public void update(List<String> logs) {
        mLogAdapter.setLogs(logs);
    }

    public boolean isLogViewShow() {
        return isLogViewShow;
    }
}
