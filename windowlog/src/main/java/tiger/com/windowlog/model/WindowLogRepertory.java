package tiger.com.windowlog.model;

import java.util.ArrayList;
import java.util.List;

import tiger.com.mkbLog.MKBLogFilter;
import tiger.com.mkbLog.MkbLog;

/**
 * Created by yinghu.gyh on 2016/5/13.
 *
 * @version 1.0
 */
public class WindowLogRepertory {
    private List<MkbLog> mLogs = new ArrayList<>();
    private List<IDataObserver> mObservers = new ArrayList<>();
    private static WindowLogRepertory mWindowLogRepertory = new WindowLogRepertory();

    private WindowLogRepertory() {
    }

    public static WindowLogRepertory getInstance() {
        if (mWindowLogRepertory == null) {
            mWindowLogRepertory = new WindowLogRepertory();
        }
        return mWindowLogRepertory;
    }

    public void registerObserver(IDataObserver observer) {
        mObservers.add(observer);
    }

    public void unregisterObserver(IDataObserver observer) {
        if (hasRegister(observer)) {
            mObservers.remove(observer);
        }
    }

    public Boolean hasRegister(IDataObserver observer) {
        return mObservers.contains(observer);
    }

    private void notifyObservers() {
        for (IDataObserver observer : mObservers) {
            observer.update(mLogs);
        }
    }

    public void updateLog(MkbLog log) {
        List<MkbLog> logList = new ArrayList<>();
        logList.add(log);
        List<MkbLog> filterList = MKBLogFilter.filterMkbLogs(logList);
        if (filterList.size() > 0) {
            addAllLogs(filterList);
            notifyObservers();
        }
    }

    public void updateLog(List<MkbLog> logs) {
        List<MkbLog> filterList = MKBLogFilter.filterMkbLogs(logs);
        if (filterList.size() > 0) {
            addAllLogs(filterList);
            notifyObservers();
        }
    }

    private void addAllLogs(List<MkbLog> logs) {
        mLogs.addAll(logs);
        if (mLogs.size() > ConfigRepertory.getInstance().getLogMaxShowCount()) {
            for (int i = 0; i < ConfigRepertory.getInstance().getLogMaxShowCount() - mLogs.size(); i++) {
                mLogs.remove(0);
            }
        }
    }

    public void clearLog() {
        mLogs.clear();
        notifyObservers();
    }
}
