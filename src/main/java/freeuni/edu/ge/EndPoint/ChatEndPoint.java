package freeuni.edu.ge.EndPoint;

import freeuni.edu.ge.Helpers.ChatDecoder;
import freeuni.edu.ge.Helpers.ChatEncoder;
import freeuni.edu.ge.Models.Chat;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@ServerEndpoint(value = "/chat/endpoint", encoders = {ChatEncoder.class}, decoders = {ChatDecoder.class})
public class ChatEndPoint {

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(final Session session, EndpointConfig endpointConfig) throws EncodeException, IOException {
        peers.add(session);
        String toJson = "{\"name\": \"Program\", \"message\" : \"Please Type Something to Start Conversation\"}";
        JsonObject jsonObject = Json.createReader(new StringReader(toJson)).readObject();
        Chat chatTmp = new Chat(jsonObject);
        session.getBasicRemote().sendObject(chatTmp);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        peers.remove(session);
    }


    @OnMessage
    public void BroadcastChat(Chat chat, Session session) throws EncodeException, IOException {
        if(session.getUserProperties().get("id") == null){
            String senderId = chat.getJson().get("senderID").toString();
            senderId = senderId.substring(1,senderId.length()-2);
            session.getUserProperties().put("id",senderId);
        }

        String recipientID = chat.getJson().get("recipientID").toString();
        recipientID = recipientID.substring(1,recipientID.length()-1);

        //view message to sender
        session.getBasicRemote().sendObject(chat);

        boolean conversationStart = false;
        for(Session peer : peers){
            if(peer.getUserProperties().get("id").equals(recipientID)){
                peer.getBasicRemote().sendObject(chat);
                conversationStart = true;
                break;
            }
        }

        if(!conversationStart){
            String toJson = "{\"name\": \"Program\", \"message\" : \"Conversation has not start yet\"}";
            JsonObject jsonObject = Json.createReader(new StringReader(toJson)).readObject();
            Chat chatTmp = new Chat(jsonObject);
            session.getBasicRemote().sendObject(chatTmp);
        }
    }

}
