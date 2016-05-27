package tiger.com.logReading.logcat;

public class Log{
	
	private String mMessage;
	private Level mLevel;
	private String mTag;
	
	public Log(String line){
		this.mMessage = line;
	}
	
	public String getMessage(){
		return mMessage;
	}
	
	public Level getLevel(){
		return mLevel;
	}
	
	public void setLevel(Level level){
		this.mLevel = level;
	}

	public String getTag() {
		return mTag;
	}

	public void setTag(String tag) {
		mTag = tag;
	}
}
