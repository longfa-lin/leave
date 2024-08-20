package com.vian.leave.service.infrastructure.socketio;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketIOEventListener {

    private final SocketIOServer socketIOServer;

    @Autowired
    public SocketIOEventListener(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
        this.socketIOServer.addConnectListener(onConnect());
        this.socketIOServer.addDisconnectListener(onDisconnect());
        this.socketIOServer.addEventListener("message", String.class, onMessage());
    }

    private ConnectListener onConnect() {
        return socketIOClient -> System.out.println("Client connected: " + socketIOClient.getSessionId());
    }

    private DisconnectListener onDisconnect() {
        return socketIOClient -> System.out.println("Client disconnected: " + socketIOClient.getSessionId());
    }

    private DataListener<String> onMessage() {
        return (socketIOClient, data, ackSender) -> {
            System.out.println("Received message: " + data);
            socketIOClient.sendEvent("message", "Received your message: " + data);
        };
    }
}