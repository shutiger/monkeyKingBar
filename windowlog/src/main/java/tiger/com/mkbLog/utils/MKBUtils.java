package tiger.com.mkbLog.utils;

import android.text.TextUtils;
import android.util.Log;

import tiger.com.mkbLog.MKBLogType;
import tiger.com.mkbLog.MkbLog;

/**
 * Created by yinghu.gyh on 2016/5/10.
 *
 * @version 1.0
 */
public class MKBUtils {
    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
