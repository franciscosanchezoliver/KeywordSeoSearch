package classes.utils;

public class Path {
	
	public static void main(String[] args) {
		Path.getProjectPath();

	}
	
	/**
	 * Get the path of this project
	 */
	public static String getProjectPath() {
		return "System.getProperty(\"user.dir\")";
	}
	
	public static String getUserPath() {
		return System.getProperty("user.dir");
	}

}
