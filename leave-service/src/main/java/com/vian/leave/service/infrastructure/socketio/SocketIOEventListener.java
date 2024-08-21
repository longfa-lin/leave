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
        // 默认命名空间
//        socketIOServer.addConnectListener(onConnect("/"));
//        socketIOServer.addDisconnectListener(onDisconnect("/"));
//        socketIOServer.addEventListener("message", String.class, onMessage("/"));

        // 自定义的 chat 命名空间
        socketIOServer.addNamespace("/chat").addConnectListener(onConnect("/chat"));
        socketIOServer.addNamespace("/chat").addDisconnectListener(onDisconnect("/chat"));
        socketIOServer.addNamespace("/chat").addEventListener("message", String.class, onMessage("/chat"));
    }

    private ConnectListener onConnect(String namespace) {
        return socketIOClient -> System.out.println("Client connected to " + namespace + ": " + socketIOClient.getSessionId());
    }

    private DisconnectListener onDisconnect(String namespace) {
        return socketIOClient -> System.out.println("Client disconnected from " + namespace + ": " + socketIOClient.getSessionId());
    }

    private DataListener<String> onMessage(String namespace) {
        return (socketIOClient, data, ackSender) -> {
            System.out.println("Message from " + namespace + ": " + data);
            socketIOClient.getNamespace().getBroadcastOperations().sendEvent("message", "Received your message: " + data);
        };
    }
}