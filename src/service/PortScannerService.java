package service;

import java.net.InetSocketAddress;
import java.net.Socket;

public class PortScannerService {

    public static boolean isPortOpen(String host, int port, int timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
