package freeuni.edu.ge.Helpers;

import freeuni.edu.ge.Models.Chat;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatEncoder implements Encoder.Text<Chat>{
    @Override
    public String encode(Chat chat) throws EncodeException {
        return chat.getJson().toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
