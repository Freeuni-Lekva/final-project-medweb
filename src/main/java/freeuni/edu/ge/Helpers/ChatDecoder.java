package freeuni.edu.ge.Helpers;

import com.google.gson.Gson;
import freeuni.edu.ge.Models.Chat;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class ChatDecoder implements Decoder.Text<Chat>{
    private static final Gson gson = new Gson();

    @Override
    public Chat decode(String s) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
        return new Chat(jsonObject);
    }

    @Override
    public boolean willDecode(String s) {
        try {
            gson.fromJson(s, Object.class);
            return true;
        } catch(com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
