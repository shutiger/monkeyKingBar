package tiger.com.logprinter;

/**
 * Created by yinghu.gyh on 2016/5/10.
 *
 * @version 1.0
 */
public class MKBLogType {
    // 普通等级log
    public static final int V = 0x1;
    public static final int D = 0x2;
    public static final int I = 0x3;
    public static final int W = 0x4;
    public static final int E = 0x5;
    public static final int A = 0x6;
    // Json Log
    public static final int JSON = 0x7;
    // Xml Log
    public static final int XML = 0x8;
    // 最高等级，保留字段
    public static final int TIGER = 0x9;
}
