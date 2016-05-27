package tiger.com.logReading.logcat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
	
	private Pattern mLevelPattern;

	public LogParser(Format format){
		this.mLevelPattern = format.getPattern();
	}
	
	public Log parseLine(String line){
		Log log = new Log(line);
		log.setLevel(getLevel(line));
		log.setTag(getTag(line));
		return log;
	}

	private String getTag(String line) {
		if (!line.contains("/") || !line.contains(": ")) {
			return "tag_mkb";
		}
		int startIndex = line.indexOf("/");
		int endIndex = line.indexOf(": ");
		if (line.contains("(")) {
			// 过滤掉进程编号
			endIndex = line.indexOf("(");
		}
		return line.substring(startIndex + 1, endIndex);
	}

	private Level getLevel(String line) {
		if (mLevelPattern == null) return Level.U;
		
		Matcher m = mLevelPattern.matcher(line);
		if (m.find()) return Level.valueOf(m.group(1));
					
		//UNKNOWN LEVEL
		return Level.U;
	}

    public static String getTimeStamp(Log log){
        String line = log.getMessage();
        return line.substring(0, 17);

    }
}
