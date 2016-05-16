package tiger.com.windowlog.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinghu.gyh on 2016/5/13.
 *
 * @version 1.0
 */
public class WindowLogRepertory {
    private List<String> mLogs = new ArrayList<>();
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
        mObservers.remove(observer);
    }

    private void notifyObservers() {
        for (IDataObserver observer : mObservers) {
            observer.update(mLogs);
        }
    }

    public void updateLog(String log) {
        mLogs.add(log);
        notifyObservers();
    }

    public void clearLog() {
        mLogs.clear();
        notifyObservers();
    }
}
