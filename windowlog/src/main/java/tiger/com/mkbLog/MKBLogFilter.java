package tiger.com.mkbLog;

import java.util.ArrayList;
import java.util.List;

/**
 * //todo 由客户端设置过滤规则
 * Created by yinghu.gyh on 2016/5/23.
 * 如果showlist有数据，先过滤出要显示的log,再过滤要忽略的
 * @version 1.0
 */
public class MKBLogFilter {
    private static final List<String> mIgnoreList = new ArrayList<>();
    private static final List<String> mShowList = new ArrayList<>();
    private static final List<Integer> mShowTypesList = new ArrayList<>();

    static {
        mIgnoreList.add("GET_TASKS");
        mIgnoreList.add("WifiStateMachine");
//        mShowList.add("");
        mShowTypesList.add(MKBLogType.JSON);
        mShowTypesList.add(MKBLogType.WDM);
    }

    public static void addIgnoreString(String str) {
        mIgnoreList.add(str);
    }

    public static void clearIgnoreList() {
        mIgnoreList.clear();
    }
    public static void addShowType(int type) {
        mShowTypesList.add(type);
    }

    public static void clearShowTypeList() {
        mShowTypesList.clear();
    }

    public static void addShowString(String str) {
        mShowList.add(str);
    }

    public static void clearShowList() {
        mShowList.clear();
    }

    private static Boolean needShow(MkbLog mkbLog) {
        if (mShowTypesList.size() == 0 && mShowList.size() == 0) {
            return true;
        }
        if (mShowTypesList.contains(mkbLog.getLogType())) {
            return true;
        }
        for (String s : mShowList) {
            if (mkbLog.getLogMessage().contains(s)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean needIgnore(MkbLog mkbLog) {
        for (String s : mIgnoreList) {
            if (mkbLog.getLogMessage().contains(s)) {
                return true;
            }
        }
        return false;
    }

    public static List<MkbLog> filterMkbLogs(List<MkbLog> mkbLogList) {
        List<MkbLog> newMkbLogList = new ArrayList<>();
        for (MkbLog mkbLog : mkbLogList) {
            if (needShow(mkbLog) && !needIgnore(mkbLog)) {
                newMkbLogList.add(mkbLog);
            }
        }
        return newMkbLogList;
    }
}
