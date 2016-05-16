package tiger.com.windowlog.model;

import java.util.List;

/**
 * Created by yinghu.gyh on 2016/5/16.
 *
 * @version 1.0
 */
public interface IDataObserver {
    void update(List<String> logs);
}
