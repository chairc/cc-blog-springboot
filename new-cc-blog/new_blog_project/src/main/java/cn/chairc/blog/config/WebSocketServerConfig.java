package cn.chairc.blog.config;

import cn.chairc.blog.entity.common.ResultSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chairc
 * @date 2021/8/3 22:03
 */
@ServerEndpoint(value = "/websocket", configurator = WebSocketConfig.class)
@Component
public class WebSocketServerConfig {

    private static Logger log = LoggerFactory.getLogger(WebSocketServerConfig.class);

    private static final AtomicInteger onlineCount = new AtomicInteger(0);
    //  concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<Session>();

    /**
     * 初始化
     */

    @PostConstruct
    public void init() {
        log.info("websocket is running success...");
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param session 加入连接的session
     * @throws IOException IO异常
     */

    @OnOpen
    public void onOpen(Session session) throws IOException {
        ResultSet resultSet = new ResultSet();
        //  在数据集中添加新打开的session
        SessionSet.add(session);
        String username = (String) session.getUserProperties().get("username");
        String userPrivateId = (String) session.getUserProperties().get("userPrivateId");
        String userEmail = (String) session.getUserProperties().get("userEmail");
        BroadCastInfo(resultSet);
    }

    /**
     * 连接关闭调用的方法
     *
     * @param session 加入连接的session
     * @throws IOException IO异常
     */

    @OnClose
    public void onClose(Session session) throws IOException {
        ResultSet resultSet = new ResultSet();
        //  在数据集中移除关闭调用的session
        SessionSet.remove(session);
        String username = (String) session.getUserProperties().get("username");
        String userPrivateId = (String) session.getUserProperties().get("userPrivateId");
        String userEmail = (String) session.getUserProperties().get("userEmail");
        BroadCastInfo(resultSet);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 加入连接的session
     */

    @OnMessage
    public void onMessage(String message, Session session) {
        ResultSet resultSet = new ResultSet();
        SendMessage(session, resultSet);
    }

    /**
     * 出现错误
     *
     * @param session 加入连接的session
     * @param error   错误
     */

    @OnError
    public void onError(Session session, Throwable error) {
        //log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息
     *
     * @param session 加入连接的session
     * @param object      返回类
     */

    private static void SendMessage(Session session, Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            session.getBasicRemote().sendText(objectMapper.writeValueAsString(object));
        } catch (Exception e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 群发消息（单对多聊天）
     *
     * @param object      返回类
     * @throws IOException IO异常
     */

    public static void BroadCastInfo(Object object) throws IOException {
        for (Session session : SessionSet) {
            if (session.isOpen()) {
                SendMessage(session, object);
            }
        }
    }

    /**
     * 指定Session发送消息（单对单聊天）
     *
     * @param sessionId 加入连接的session
     * @param object      返回类
     * @throws IOException IO异常
     */

    public static void SendMessage(Object object, String sessionId) throws IOException {
        Session session = null;
        for (Session s : SessionSet) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            SendMessage(session, object);
        } else {
            log.warn("没有找到你指定ID的会话：{}", sessionId);
        }
    }
}
