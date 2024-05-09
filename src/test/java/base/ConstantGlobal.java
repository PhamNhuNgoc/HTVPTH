package base;

import helpers.PropertiesHelpers;
import helpers.SystemHelpers;

import java.io.File;

public class ConstantGlobal {
    static {
        PropertiesHelpers.loadAllFiles();
    }

    public static final String YES = "yes";
    public static final String NO = "no";

    public static final String PROJECT_PATH = SystemHelpers.getCurrentDir();

    public final static String BROWSER = PropertiesHelpers.getValue("BROWSER");
    public final static boolean HEADLESS = Boolean.parseBoolean(PropertiesHelpers.getValue("HEADLESS"));

    public final static String ENV = PropertiesHelpers.getValue("ENV");
    public static final String LOCATE = PropertiesHelpers.getValue("LOCATE");
    public final static String AUTHOR = PropertiesHelpers.getValue("AUTHOR");
    public final static String URL = PropertiesHelpers.getValue("URL");
    public final static String USERNAME = PropertiesHelpers.getValue("USERNAME");
    public final static String PASSWORD = PropertiesHelpers.getValue("PASSWORD");

    public final static long STEP_TIME = Long.parseLong(PropertiesHelpers.getValue("STEP_TIME"));
    public final static String ACTIVE_PAGE_LOADED = PropertiesHelpers.getValue("ACTIVE_PAGE_LOADED");
    public final static long EXPLICIT_TIMEOUT = Long.parseLong(PropertiesHelpers.getValue("EXPLICIT_TIMEOUT"));
    public final static long PAGE_LOAD_TIMEOUT = Long.parseLong(PropertiesHelpers.getValue("PAGE_LOAD_TIMEOUT"));

    public final static String SCREENSHOT_FAIL = PropertiesHelpers.getValue("SCREENSHOT_FAIL");
    public final static String SCREENSHOT_SKIPPED = PropertiesHelpers.getValue("SCREENSHOT_SKIPPED");
    public final static String SCREENSHOT_PASS = PropertiesHelpers.getValue("SCREENSHOT_PASS");
    public final static String SCREENSHOT_STEP = PropertiesHelpers.getValue("SCREENSHOT_STEP");
    public final static String SCREENSHOT_PATH = PropertiesHelpers.getValue("SCREENSHOT_PATH");
    public final static String RECORD_VIDEO = PropertiesHelpers.getValue("RECORD_VIDEO");
    public final static String RECORD_VIDEO_PATH = PropertiesHelpers.getValue("RECORD_VIDEO_PATH");

    public final static int RETRY_TEST_FAIL = Integer.parseInt(PropertiesHelpers.getValue("RETRY_TEST_FAIL"));

    public static final String TARGET = PropertiesHelpers.getValue("TARGET");
    public static final String REMOTE_URL = PropertiesHelpers.getValue("REMOTE_URL");
    public static final String REMOTE_PORT = PropertiesHelpers.getValue("REMOTE_PORT");

    public static final String OVERRIDE_REPORTS = PropertiesHelpers.getValue("OVERRIDE_REPORTS");
    public static final String OPEN_REPORTS_AFTER_EXECUTION = PropertiesHelpers.getValue("OPEN_REPORTS_AFTER_EXECUTION");
    public final static String EXTENT_REPORT_PATH = PropertiesHelpers.getValue("EXTENT_REPORT_PATH");
    public final static String EXTENT_REPORT_NAME = PropertiesHelpers.getValue("EXTENT_REPORT_NAME");
    public static final String REPORT_TITLE = PropertiesHelpers.getValue("REPORT_TITLE");

}
