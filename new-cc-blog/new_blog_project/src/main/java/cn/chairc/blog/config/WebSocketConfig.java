package cn.chairc.blog.config;

import cn.chairc.blog.utils.CommonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author chairc
 * @date 2021/8/3 21:58
 */
@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator {

    /**
     * 服务器端点用户对WebSocket使用
     *
     * @return ServerEndpointExporter
     */

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 拦截握手请求
     *
     * @param serverEndpointConfig 服务器端点配置
     * @param request              握手请求
     * @param response             握手回复
     */

    @Override
    public void modifyHandshake(ServerEndpointConfig serverEndpointConfig, HandshakeRequest request, HandshakeResponse response) {
        //  将用户信息存储到socket的配置里
        serverEndpointConfig.getUserProperties().put("username", CommonUtil.sessionValidate("username"));
        serverEndpointConfig.getUserProperties().put("userPrivateId", CommonUtil.sessionValidate("userPrivateId"));
        serverEndpointConfig.getUserProperties().put("userEmail", CommonUtil.sessionValidate("userEmail"));
        super.modifyHandshake(serverEndpointConfig, request, response);
    }
}
