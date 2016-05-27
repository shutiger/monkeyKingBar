package tiger.com.logReading.util;

import android.content.Context;
import android.widget.Toast;

import com.stericson.RootShell.RootShell;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import tiger.com.logReading.logcat.Log;
import tiger.com.mkbLog.MkbLog;
import tiger.com.windowlog.R;

/**
 * Created by yinghu.gyh on 2016/5/19.
 *
 * @version 1.0
 */
public class LogReadingUtils {
    public static void getPermissionViaRoot(final Context appContext) {
        Command command = new Command(0, ("pm grant " + appContext.getPackageName() + " android.permission.READ_LOGS | echo \"logcat\"")) {
            private boolean success = true;
            @Override
            public void commandOutput(int id, String line) {
                if (line.toLowerCase().contains("exception")) success = false;
                super.commandOutput(id, line);
            }
            @Override
            public void commandTerminated(int id, String reason) {
                Toast.makeText(appContext, R.string.text_root_error, Toast.LENGTH_LONG).show();
            }
            @Override
            public void commandCompleted(int id, int exitCode) {
                Toast.makeText(appContext, success ? "success" : "error", Toast.LENGTH_SHORT).show();
                if (success) {
                    PrefUtils.setWizardDone(appContext, true);
                }
                else Toast.makeText(appContext, R.string.text_root_error, Toast.LENGTH_LONG).show();
            }
        };
        try {
            RootShell.getShell(true).add(command);
        } catch (IOException | RootDeniedException | TimeoutException e) {
            Toast.makeText(appContext, R.string.text_root_error, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public static List<MkbLog> mapMkbLog(List<Log> logs) {
        List<MkbLog> mkbLogs = new ArrayList<>();
        for (Log log : logs) {
            mkbLogs.add(MkbLog.buildMkbLog(log.getLevel().getValue(), log.getTag(), log.getMessage()));
        }
        return mkbLogs;
    }

}
