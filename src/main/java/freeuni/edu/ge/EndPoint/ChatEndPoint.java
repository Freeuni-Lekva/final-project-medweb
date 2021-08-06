package freeuni.edu.ge.EndPoint;

import freeuni.edu.ge.Helpers.ChatDecoder;
import freeuni.edu.ge.Helpers.ChatEncoder;
import freeuni.edu.ge.Models.Chat;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@ServerEndpoint(value = "/chat/endpoint", encoders = {ChatEncoder.class}, decoders = {ChatDecoder.class})
public class ChatEndPoint {

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(final Session session, EndpointConfig endpointConfig) {
        peers.add(session);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        peers.remove(session);
    }


    @OnMessage
    public void BroadcastChat(Chat chat, Session session) throws EncodeException, IOException {
        System.out.println("Broadcast Chat:  " + chat);
        for(Session peer : peers){
            peer.getBasicRemote().sendObject(chat);
        }
    }

}
