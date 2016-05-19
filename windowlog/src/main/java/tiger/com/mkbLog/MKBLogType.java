package tiger.com.mkbLog;

/**
 * Created by yinghu.gyh on 2016/5/10.
 *
 * @version 1.0
 */
public class MKBLogType {
    // Normal Log
    public static final int Normal = 0x10;
    // Json Log
    public static final int JSON = 0x11;
    // wdm Log
    public static final int WDM = 0x12;
//    // Xml Log (目前无用武之地)
//    public static final int XML = 0x12;
    // 最高等级，保留字段
    public static final int TIGER = 0x100;

    public static final String MARK_WDM_PAGE = "||2001";
    public static final String MARK_WDM_NORMAL_EVENT = "||19999";
    public static final String MARK_JSON_BEGIN = "{";
    public static final String MARK_JSON_END = "}";

    public static int getLogType(String logMessage) {
        if (logMessage.contains(MKBLogType.MARK_WDM_NORMAL_EVENT) || logMessage.contains(MKBLogType.MARK_WDM_PAGE)) {
            return MKBLogType.WDM;
        } else if (logMessage.contains(MKBLogType.MARK_JSON_BEGIN) && logMessage.contains(MKBLogType.MARK_JSON_END)) {
            return MKBLogType.JSON;
        } else {
            return MKBLogType.Normal;
        }
    }
}
