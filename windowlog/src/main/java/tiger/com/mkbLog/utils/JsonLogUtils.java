package tiger.com.mkbLog.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yinghu.gyh on 2016/5/10.
 *
 * @version 1.0
 */
public class JsonLogUtils {
    private static final int JSON_INDENT = 4;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * 打印新的转换成json格式的log
     * @param tag log的tag信息
     * @param msg log主体
     * @param headString json顶部显示的信息（例如类名）
     */
    public static void printJson(String tag, String msg, String headString) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        MKBUtils.printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        MKBUtils.printLine(tag, false);
    }

    /**
     * 将log转成json格式的字符串
     * @param msg msg
     * @param headString log头部显示的信息（例如接口名类名等）
     * @return json格式的字符串
     */
    public static String transformJsonStyle(String msg, String headString) {
        String message;
        StringBuilder messageBulider = new StringBuilder("");
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        messageBulider.append("╔══════════════════ \n");
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            messageBulider.append("║ " + line + " \n");
        }
        messageBulider.append("╚══════════════════");
        return messageBulider.toString();
    }
}
