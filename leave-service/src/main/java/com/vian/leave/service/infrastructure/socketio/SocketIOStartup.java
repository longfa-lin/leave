package com.vian.leave.service.infrastructure.socketio;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.boot.CommandLineRunner;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  

@Configuration  
public class SocketIOStartup {  
    
    @Bean  
    public CommandLineRunner runner(SocketIOServer server) {  
        return args -> {  
            server.start();  
            
            Runtime.getRuntime().addShutdownHook(new Thread(server::stop));  
        };  
    }  
}