package tiger.com.logprinter.mkblog;

import android.util.Log;

import tiger.com.logprinter.MKBLogType;

/**
 * Created by yinghu.gyh on 2016/5/10.
 *
 * @version 1.0
 */
public class NormalLog {

    public static void print(int type, String tag, String msg) {

        int index = 0;
        int maxLength = 4000;
        int countOfSub = msg.length() / maxLength;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                printMsg(type, tag + " (part " + i + ")", sub);
                index += maxLength;
            }
            printMsg(type, tag + "(part end)", msg.substring(index, msg.length()));
        } else {
            printMsg(type, tag, msg);
        }
    }

    private static void printMsg(int type, String tag, String msg) {
        switch (type) {
            case MKBLogType.V:
                Log.v(tag, msg);
                break;
            case MKBLogType.D:
                Log.d(tag, msg);
                break;
            case MKBLogType.I:
                Log.i(tag, msg);
                break;
            case MKBLogType.W:
                Log.w(tag, msg);
                break;
            case MKBLogType.E:
                Log.e(tag, msg);
                break;
            case MKBLogType.A:
                Log.wtf(tag, msg);
                break;
        }
    }
}
