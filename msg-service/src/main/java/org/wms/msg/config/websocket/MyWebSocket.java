package org.wms.msg.config.websocket;

import cn.hutool.json.JSONUtil;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.wms.msg.vo.WsMsgDataVO;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/ws/{clientId}")
public class MyWebSocket {


    private static final Logger log = LoggerFactory.getLogger(MyWebSocket.class);
    /**
     * session list
     */
    private static ConcurrentHashMap<String, Session> sessionList = new ConcurrentHashMap<>();
    /**
     * 当前 clientId
     */
    private String currentClientId = "";


    @OnOpen
    public void open(Session session, @PathParam("clientId") String clientId) throws IOException {
        sessionList.remove(clientId);
        sessionList.put(clientId, session);
        currentClientId = clientId;
        this.sendMsg(session, "connectok");
    }

    @OnClose
    public void close(Session session) throws IOException {
        sessionList.remove(currentClientId);
        System.out.println("连接关闭，session=" + JSONUtil.toJsonStr(session));
    }

    @OnMessage
    public void receiveMsg(Session session, String msg) throws IOException {
        this.sendMsg(session, "接收到的消息为：" + msg);
    }

    @OnError
    public void error(Session session, Throwable e) throws IOException {
        System.out.println("连接异常，session=" + JSONUtil.toJsonStr(session) + ";currentClientId=" + currentClientId);
        this.sendMsg(session, "发生异常,e=" + e.getMessage());
    }

    /**
     * @param clientId
     * @param msg
     */
    public boolean sendMsg(String clientId, String msg) throws IOException {
        if (sessionList.containsKey(clientId)) {
            Session session = sessionList.get(clientId);
            this.sendMsg(session, msg);
            return true;
        } else {
            return false;
        }
    }

    private void sendMsg(Session session, String msg) throws IOException {
        session.getBasicRemote().sendText(msg);
    }

    @Scheduled(fixedRate = 30000) // 每 30 秒发送一次心跳
    public void sendHeartbeat() {
        log.info("触发心跳定时任务");
        sessionList.forEach((clientId, session) -> {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(JSONUtil.toJsonStr(new WsMsgDataVO<>(null, 0)));
                } catch (IOException e) {
                    log.error("心跳消息发送失败,userId:{}, {}", clientId, e.getMessage());
                }
            }
        });
    }
}
