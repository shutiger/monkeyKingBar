package tiger.com.windowlog.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by yinghu.gyh on 2016/5/10.
 *
 * @version 1.0
 */
public class WindowLog {
    private static final int TEXT_COLOR = 0xffffffff;
    private static final int TEXT_SHADOW = 0xff000000;
    private static final float TEXT_SIZE = 13.0f;
    private static final float SHADOW_RADIUS = 2.0f;
    private static final int BACKGROUND_COLOR = 0x80000000;
    private Context mContext;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLogTextParams;
    private boolean mTestMode = true;
    private TextView mTextView;
    private static WindowLog mWindowLogInstance = new WindowLog();

    private WindowLog(){}

    public static WindowLog getInstance() {
        if (mWindowLogInstance == null) {
            mWindowLogInstance = new WindowLog();
        }
        return mWindowLogInstance;
    }

    public void init(Context context) {
        mContext = context;
    }

    public void showView() {
        if (mTestMode && mTextView == null) {
            createView(mContext);
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            mLogTextParams = new WindowManager.LayoutParams();
            mLogTextParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mLogTextParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            mLogTextParams.format = PixelFormat.TRANSLUCENT;
            mLogTextParams.gravity = Gravity.LEFT;
            mLogTextParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            mLogTextParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
            mWindowManager.addView(mTextView, mLogTextParams);
        }
    }

    private void createView(Context context) {
        mTextView = new TextView(context);
        mTextView.setText("\nOh my God, nothing!!!\n");
        mTextView.setTextSize(TEXT_SIZE);
        mTextView.setFocusable(false);
        mTextView.setTextColor(TEXT_COLOR);
        mTextView.setBackgroundColor(BACKGROUND_COLOR);
        mTextView.setShadowLayer(SHADOW_RADIUS, 2.0f, 2.0f, TEXT_SHADOW);
        mTextView.setGravity(Gravity.LEFT);
        mTextView.setPadding(20, 0, 150, 0);
    }

    public void log(String log) {
        mTextView.setText(log);
    }
}
