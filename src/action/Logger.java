package action;
import java.text.SimpleDateFormat;

public class Logger {

	public static final String ansicht_reset = "\u001B[0m";
	public static final String black = "\u001B[30m";
	public static final String red = "\u001B[31m";
	public static final String green = "\u001B[32m";
	public static final String yellow = "\u001B[33m";
	public static final String blue = "\u001B[34m";
	public static final String purple = "\u001B[35m";
	public static final String cyan = "\u001B[36m";
	public static final String white = "\u001B[37m";
	
	public static void logMessage(String message, Object caller){
		System.out.println(green + "[Msg][" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())
				+ "][From: " + caller.getClass().getSimpleName() + "]\t " + white + message + ansicht_reset);
	}
	
	public static void logInfo(String message, Object caller){
		System.out.println(yellow + "[Inf]" + green + "[" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())
				+ "][From: " + caller.getClass().getSimpleName() + "]\t " + message + ansicht_reset);
	}

	public static void logWarning(String message, Object caller){
		System.out.println(yellow + "[War][" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())
				+ "][From: " + caller.getClass().getSimpleName() + "]\t " + message + ansicht_reset);
	}
	
	public static void logMessage(String message){
		System.out.println(green + "[Msg][" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())
				+ "]\t " + white + message + ansicht_reset);
	}
	
	public static void logInfo(String message){
		System.out.println(yellow + "[Inf]" + green + "[" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())
				+ "]\t " + message + ansicht_reset);
	}

	public static void logWarning(String message){
		System.out.println(yellow + "[War][" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())
				+ "]\t " + message + ansicht_reset);
	}
	
	public static void logError(String message, Object caller){
		System.out.println(red + "[Err][" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())
				+ "][From: " + caller.getClass().getSimpleName() + "]\t " + message + ansicht_reset);
	}
}