package dev.danielrl.atomictrip.dto;

public class Message {

    private String verb;
    private String path;
    private String body;
    

    public Message(String verb, String path, String body) {
        this.verb = verb;
        this.body = body;
        this.path = path;
    }

    public String getVerb() {
        return verb;
    }

    public String getPath() {
        return path;
    }


    public String getBody() {
        return body;
    }

}    
