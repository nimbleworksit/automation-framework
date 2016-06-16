package com.ensono.app.bean;

public class GridNode {

    private String name;
    private String browser;
    private String browserVersion;
    private String operatingSystem;

    private String outputExcelFilePath;
    private String screenshotOutputReportDirPath;
    private String logDirPath;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(final String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(final String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(final String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getOutputExcelFilePath() {
        return outputExcelFilePath;
    }

    public void setOutputExcelFilePath(final String outputExcelFilePath) {
        this.outputExcelFilePath = outputExcelFilePath;
    }

    public String getScreenshotOutputReportDirPath() {
        return screenshotOutputReportDirPath;
    }

    public void setScreenshotOutputReportDirPath(final String screenshotOutputReportDirPath) {
        this.screenshotOutputReportDirPath = screenshotOutputReportDirPath;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append("_").append(browser).append("_").append(browserVersion).append("_")
                .append(operatingSystem);
        return stringBuilder.toString();
    }

    public String getLogDirPath() {
        return logDirPath;
    }

    public void setLogDirPath(final String logDirPath) {
        this.logDirPath = logDirPath;
    }
}