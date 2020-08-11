package mobile.core;

public class AndroidDevice {
    private String udid;
    private int systemPort;
    private String platformVersion;
    private int port;

    public boolean getReal() {
        return isReal;
    }

    public void setReal(boolean real) {
        isReal = real;
    }

    private boolean isReal;
    public AndroidDevice() {

    }

    public boolean isPortStarted() {
        return false;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public int getSystemPort() {
        return systemPort;
    }

    public void setSystemPort(int systemPort) {
        this.systemPort = systemPort;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

