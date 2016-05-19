package tiger.com.windowlog.view;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import tiger.com.mkbLog.MkbLog;
import tiger.com.windowlog.model.ConfigRepertory;
import tiger.com.windowlog.model.IDataObserver;
import tiger.com.windowlog.view.adapter.LogItemAdapter;
import tiger.com.windowlog.view.viewinterface.IWindowListLogView;


/**
 * Created by yinghu.gyh on 2016/5/11.
 *
 * @version 1.0
 */
public class WindowListLogView implements IWindowListLogView, IDataObserver {
    private Context mContext;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLogListParams;
    private boolean isLogViewShow = false;
    private ListView mListView;
    private LogItemAdapter mLogAdapter;
    private Button mHideButton;
    private WindowManager.LayoutParams mHideButtonParams;
    private Button mPassThroughButton;
    private WindowManager.LayoutParams mPassThroughButtonParams;

    public WindowListLogView(Context context){
        mContext = context;
    }

    @Override
    public void showView() {
        if (mListView == null) {
            mLogAdapter = new LogItemAdapter(mContext);
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

            mListView = WindowViewFactory.buildWindowListView(mContext, mLogAdapter);
            mLogListParams = WindowViewFactory.buildWindowListLogParams();
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
            mHideButtonParams = WindowViewFactory.buildHideButtonLayoutParams();
            mWindowManager.addView(mHideButton, mHideButtonParams);

            mPassThroughButton = WindowViewFactory.buildPassThroughButton(mContext);
            mPassThroughButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("透".equals(mPassThroughButton.getText())) {
                        resetLogListParamsFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        mPassThroughButton.setText("浮");
                    } else {
                        resetLogListParamsFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
                        mPassThroughButton.setText("透");
                    }
                }
            });
            mPassThroughButtonParams = WindowViewFactory.buildPassThroughButtonLayoutParams();
            mWindowManager.addView(mPassThroughButton, mPassThroughButtonParams);

            isLogViewShow = true;
        }
    }

    private void resetLogListParamsFlags(int flags) {
        removeAllView();
        mLogListParams.flags = flags;
        addAllViewByOrder();
    }

    private void removeAllView() {
        mWindowManager.removeView(mHideButton);
        mWindowManager.removeView(mPassThroughButton);
        mWindowManager.removeView(mListView);
    }

    private void addAllViewByOrder() {
        mWindowManager.addView(mListView, mLogListParams);
        mWindowManager.addView(mPassThroughButton, mPassThroughButtonParams);
        mWindowManager.addView(mHideButton, mHideButtonParams);
    }

    @Override
    public void closeView() {
        if (mContext != null) {
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            if (mListView != null) {
                wm.removeView(mListView);
                mListView = null;
            }
            if (mHideButton != null) {
                wm.removeView(mHideButton);
                mHideButton = null;
            }
            isLogViewShow = false;
        }
    }

    @Override
    public void update(List<MkbLog> logs) {
        mLogAdapter.setLogs(logs);
        if (ConfigRepertory.getInstance().isLogScrollOpen()) {
            mListView.setSelection(mListView.getBottom());
        }
    }

    public boolean isLogViewShow() {
        return isLogViewShow;
    }
}
