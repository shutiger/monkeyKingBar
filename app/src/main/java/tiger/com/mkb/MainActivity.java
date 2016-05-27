package tiger.com.mkb;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.List;

import tiger.com.logReading.logcat.Log;
import tiger.com.logReading.logcat.Logcat;
import tiger.com.logReading.util.LogReadingUtils;
import tiger.com.logReading.util.PrefUtils;
import tiger.com.mkbLog.MkbLog;
import tiger.com.mkbLog.utils.JsonLogUtils;
import tiger.com.windowlog.controller.WindowListLogController;

public class MainActivity extends AppCompatActivity {
//    private static String JSON = "{\"menu\":[\"泰式柠檬肉片\",\"鸡柳汉堡\",\"蒸桂鱼卷 \"],\"tag\":\"其他\"}";
    private static String JSON = "{\"couponId\":-1,\"itemWithQulitity\":[{\"count\":1,\"itemKey\":\"ucoiPw43OOOOOjfc\",\"skuId\":0}],\"pointToUse\":-1}";
    private Handler mLogHandler;
    private Logcat mLogcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowListLogController.getInstance().init(getApplicationContext());
        WindowListLogController.getInstance().showWindowLog();
//        findViewById(R.id.id_text).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WindowListLogController.getInstance().log(MkbLog.buildMkbLog(JsonLogUtils.transformJsonStyle(JSON, "TEST JSON")));
//            }
//        });

        LogReadingUtils.getPermissionViaRoot(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!PrefUtils.isWizardDone(getApplicationContext())) {
            return;
        }
        if (mLogHandler == null) mLogHandler = new Handler(this);
        if (mLogcat == null) mLogcat = new Logcat(
                mLogHandler,
                PrefUtils.getLevel(this),
                PrefUtils.getFormat(this),
                PrefUtils.getBuffer(this));
        mLogcat.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!PrefUtils.isWizardDone(getApplicationContext())) {
            return;
        }
        mLogHandler = null;
        if (mLogcat != null) {
            mLogcat.stop();
        }
        mLogcat = null;
    }

    private static class Handler extends android.os.Handler {

        private final WeakReference<MainActivity> mActivity;

        public Handler(MainActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity == null) return;
            switch (msg.what){
                case Logcat.CAT_LOGS:
                    List<Log> catLogs = (List<Log>) msg.obj;
                    WindowListLogController.getInstance().log(LogReadingUtils.mapMkbLog(catLogs));
                    break;
                case Logcat.CLEAR_LOGS:
                    //// TODO: 2016/5/23
                    break;
                case Logcat.REMOVE_LOGS:
                    //// TODO: 2016/5/23
                    break;
            }
        }

    }
}
