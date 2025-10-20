package org.howard.edu.lsp.midterm.question4;

/**
 * BatteryPowered interface defines behavior for devices that have battery power.
 */
public interface BatteryPowered {
    /**
     * Get the current battery percentage.
     * @return battery percentage from 0 to 100
     */
    int getBatteryPercent();
    
    /**
     * Set the battery percentage.
     * @param percent battery percentage from 0 to 100
     * @throws IllegalArgumentException if percent is outside 0-100 range
     */
    void setBatteryPercent(int percent);
}
