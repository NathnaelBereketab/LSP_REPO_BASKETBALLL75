package org.howard.edu.lsp.midterm.question4;

/**
 * Networked interface defines behavior for devices that can connect to a network.
 */
public interface Networked {
    /**
     * Connect the device to the network.
     */
    void connect();
    
    /**
     * Disconnect the device from the network.
     */
    void disconnect();
    
    /**
     * Check if the device is currently connected to the network.
     * @return true if connected, false otherwise
     */
    boolean isConnected();
}
