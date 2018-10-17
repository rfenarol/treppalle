package it.forfun.coding.tp.exception;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class ErrorInfo {
	public final String error;
	public int status;
	public final String date;

	static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
	public ErrorInfo(Throwable throwable, int status) {
		this.error = throwable.getMessage();
		this.date = GetUTCdatetimeAsString();
		this.status = status;
	}

	public ErrorInfo(String message, int status) {
		this.error = message;
		this.date = GetUTCdatetimeAsString();
		this.status = status;
	}

	@Override
	public String toString() {
		return "ErrorInfo [status=" + status + ", error=" + error + ", date=" + date + "]";
	}
	
	public static String GetUTCdatetimeAsString()
	{
	    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    final String utcTime = sdf.format(new Date());

	    return utcTime;
	}

}