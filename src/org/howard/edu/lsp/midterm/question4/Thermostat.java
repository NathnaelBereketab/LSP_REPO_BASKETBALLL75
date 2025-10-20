package org.howard.edu.lsp.midterm.question4;

/**
 * Thermostat device that extends Device and implements Networked interface.
 */
public class Thermostat extends Device implements Networked {
    private double temperatureC;

    /**
     * Constructor for Thermostat.
     * @param id device ID
     * @param location device location
     * @param initialTempC initial temperature in Celsius
     */
    public Thermostat(String id, String location, double initialTempC) {
        super(id, location);
        this.temperatureC = initialTempC;
    }

    /**
     * Get the current temperature in Celsius.
     * @return temperature in Celsius
     */
    public double getTemperatureC() {
        return temperatureC;
    }

    /**
     * Set the temperature in Celsius.
     * @param temperatureC temperature in Celsius
     */
    public void setTemperatureC(double temperatureC) {
        this.temperatureC = temperatureC;
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

    // Device abstract method implementation
    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "Thermostat[id=" + getId() + ", loc=" + getLocation() +
               ", conn=" + connStatus + ", tempC=" + temperatureC + "]";
    }
}
