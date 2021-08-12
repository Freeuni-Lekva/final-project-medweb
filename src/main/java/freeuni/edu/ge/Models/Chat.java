package freeuni.edu.ge.Models;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringWriter;

public class Chat {

    private JsonObject json;

    public Chat (JsonObject json){
        this.json = json;
    }

    public JsonObject getJson() {
        return json;
    }

    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        Json.createWriter(writer).write(json);
        return writer.toString();
    }

}
