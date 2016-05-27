package tiger.com.windowlog.model;

/**
 * Created by yinghu.gyh on 2016/5/18.
 *
 * @version 1.0
 */
public class ConfigRepertory {
    //// TODO: 2016/5/18  
    private int mLogType;
    private int mLogLevel;
    private int mLogMaxShowCount = 100;
    private int mLogMaxStoreCount = 1000;
    private boolean isLogScrollOpen = true;
    private static ConfigRepertory mConfigRepertory = new ConfigRepertory();

    private ConfigRepertory() {

    }

    public static ConfigRepertory getInstance() {
        if (mConfigRepertory == null) {
            mConfigRepertory = new ConfigRepertory();
        }
        return mConfigRepertory;
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

    public int getLogMaxShowCount() {
        return mLogMaxShowCount;
    }

    public void setLogMaxShowCount(int logMaxShowCount) {
        mLogMaxShowCount = logMaxShowCount;
    }

    public int getLogMaxStoreCount() {
        return mLogMaxStoreCount;
    }

    public void setLogMaxStoreCount(int logMaxStoreCount) {
        mLogMaxStoreCount = logMaxStoreCount;
    }

    public boolean isLogScrollOpen() {
        return isLogScrollOpen;
    }

    public void setLogScrollOpen(boolean logScrollOpen) {
        isLogScrollOpen = logScrollOpen;
    }
}
