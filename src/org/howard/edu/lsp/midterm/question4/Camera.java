package org.howard.edu.lsp.midterm.question4;

/**
 * Camera device that extends Device and implements Networked and BatteryPowered interfaces.
 */
public class Camera extends Device implements Networked, BatteryPowered {
    private int batteryPercent;

    /**
     * Constructor for Camera.
     * @param id device ID
     * @param location device location
     * @param initialBattery initial battery percentage (0-100)
     */
    public Camera(String id, String location, int initialBattery) {
        super(id, location);
        setBatteryPercent(initialBattery);
    }

    // Networked interface implementation
    @Override
    public void connect() {
        setConnected(true);
    }

    @Override
    public void disconnect() {
        setConnected(false);
    }

    @Override
    public boolean isConnected() {
        return super.isConnected();
    }

    // BatteryPowered interface implementation
    @Override
    public int getBatteryPercent() {
        return batteryPercent;
    }

    @Override
    public void setBatteryPercent(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("battery 0..100");
        }
        this.batteryPercent = percent;
    }

    // Device abstract method implementation
    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "Camera[id=" + getId() + ", loc=" + getLocation() +
               ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
    }
}
