package helpers;

import base.ConstantGlobal;
import driver.DriverManager;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.testng.ITestResult;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class CaptureHelpers extends ScreenRecorder{

    private final static String RECORD_FOLDER = "./logs/test-recordings/";
    private final static String SCREEN_SHOT_FOLDER ="./logs/screen-shot/";

    public static ScreenRecorder screenRecorder;
    public String name;

    public CaptureHelpers(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat,
                       Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        return new File(movieFolder,
                name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
    }

    public static void startRecord(String methodName) throws Exception {
        //Tạo thư mục để lưu file video vào
        File file = new File(RECORD_FOLDER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();
        screenRecorder = new CaptureHelpers(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file, methodName);
        screenRecorder.start();
    }

    public static void stopRecord() {
        try{
        screenRecorder.stop();
        }
        catch (Exception e){
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            Log.error(e.getMessage());
        }
    }

    public static void takeScreenshot(ITestResult iTestResult)  {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        File theDir = new File(SCREEN_SHOT_FOLDER);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        try {
            //Convert epoch to local time
            Instant instant = Instant.ofEpochMilli(iTestResult.getStartMillis());
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDate = instant.atZone(zoneId).toLocalDateTime();
            String strLocalDate = localDate.toString().replace(":", ".");

            File destFile = new File(String.format(SCREEN_SHOT_FOLDER + "%s-%s.png", iTestResult.getName(), strLocalDate));
            FileUtils.copyFile(source, destFile);
            //FileHandler.copy(source, new File("./screenshots/" + iTestResult.getName() + ".png"));

        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            Log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot taken: " + iTestResult.getName());
    }

    //Take a screenshot
    public static void takeScreenshot(String name) {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        File theDir = new File(SCREEN_SHOT_FOLDER);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        try {
            FileUtils.copyFile(source, new File(SCREEN_SHOT_FOLDER + name + ".png"));
            //FileHandler.copy(source, new File(SCREEN_SHOT_FOLDER + name + ".png"));
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + name);
            Log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot taken: " + name);
    }

    public static File getScreenshotFile(String screenshotName) {
        Rectangle allScreenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(allScreenBounds);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        String path = SystemHelpers.getCurrentDir() + ConstantGlobal.EXTENT_REPORT_PATH + File.separator + "images";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
            Log.info("Folder created: " + folder);
        }

        String filePath = path + File.separator + screenshotName + dateName + ".png";
        File file = new File(filePath);
        try {
            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public static String getScreenshotRelativePath(String screenshotName) {
        Rectangle allScreenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(allScreenBounds);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        String path = SystemHelpers.getCurrentDir() + ConstantGlobal.EXTENT_REPORT_PATH + File.separator + "images";

        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
            Log.info("Folder created: " + folder);
        }

        String filePath = path + File.separator + screenshotName + dateName + ".png";

        File file = new File(filePath);
        try {
            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String filePathRelative = ConstantGlobal.EXTENT_REPORT_PATH + File.separator + "images" + File.separator + screenshotName + dateName + ".png";
        return filePathRelative;
    }

    public static String getScreenshotAbsolutePath(String screenshotName) {
        Rectangle allScreenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(allScreenBounds);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        String path = SystemHelpers.getCurrentDir() + ConstantGlobal.EXTENT_REPORT_PATH + File.separator + "images";

        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
            Log.info("Folder created: " + folder);
        }

        String filePath = path + File.separator + screenshotName + dateName + ".png";

        File file = new File(filePath);
        try {
            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filePath;
    }
}
