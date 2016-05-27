package tiger.com.mkbLog;

import org.json.JSONException;
import org.json.JSONObject;

import tiger.com.mkbLog.utils.JsonLogUtils;

/**
 * Created by yinghu.gyh on 2016/5/18.
 *
 * @version 1.0
 */
public class MkbLog {
    private int mLogType;
    private int mLogLevel;
    private String mLogTag;
    private String mLogMessage;

    private MkbLog(String logMessage) {
        this("tag_tiger", logMessage);
    }

    private MkbLog(String logTag, String logMessage) {
        this(MKBLogLevel.E, logTag, logMessage);
    }

    private MkbLog(int logLevel, String logTag, String logMessage) {
        this(MKBLogType.getLogType(logMessage), logLevel, logTag, logMessage);
    }

    private MkbLog(int logType, int logLevel, String logTag, String logMessage) {
        mLogType = logType;
        mLogLevel = logLevel;
        mLogTag = logTag;
        mLogMessage = logMessage;
        formatLogMessage();
    }

    private void formatLogMessage() {
        if (MKBLogType.JSON == mLogType) {
            int begin = mLogMessage.indexOf("{");
            int end = mLogMessage.lastIndexOf("}");
            String jsonStr = mLogMessage.substring(begin, end + 1);
            try {
                new JSONObject(jsonStr);
                mLogMessage = JsonLogUtils.transformJsonStyle(jsonStr, mLogTag);
            } catch (JSONException e) {
                mLogType = MKBLogType.Normal;
            }
        } else if (MKBLogType.WDM == mLogType) {
            //TODO
        }
    }

    /**
     * 构建一个MKBLog
     * @param logMessage log信息
     * @return MKBLog
     */
    public static MkbLog buildMkbLog(String logMessage) {
        return new MkbLog(logMessage);
    }

    /**
     * 构建一个MKBLog
     * @param logTag tag
     * @param logMessage message
     * @return MKBLog
     */
    public static MkbLog buildMkbLog(String logTag, String logMessage) {
        return new MkbLog(logTag, logMessage);
    }

    /**
     * 构建一个MKBLog
     * @param logLevel level
     * @param logTag tag
     * @param logMessage message
     * @return
     */
    public static MkbLog buildMkbLog(int logLevel, String logTag, String logMessage) {
        return new MkbLog(logLevel, logTag, logMessage);
    }

    /**
     * 构建一个MKBLog
     * @param logType type
     * @param logLevel level
     * @param logTag tag
     * @param logMessage message
     * @return
     */
    public static MkbLog buildMkbLog(int logType, int logLevel, String logTag, String logMessage) {
        return new MkbLog(logType, logLevel, logTag, logMessage);
    }

    public int getLogType() {
        return mLogType;
    }

    public void setLogType(int logType) {
        mLogType = logType;
    }

    public int getLogLevel() {
        return mLogLevel;
    }

    public void setLogLevel(int logLevel) {
        mLogLevel = logLevel;
    }

    public String getLogTag() {
        return mLogTag;
    }

    public void setLogTag(String logTag) {
        mLogTag = logTag;
    }

    public String getLogMessage() {
        return mLogMessage;
    }

    public void setLogMessage(String logMessage) {
        mLogMessage = logMessage;
    }

}
