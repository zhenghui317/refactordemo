package com.dingjust.demo.socket;

/**
 * Created by dingjust on 2018/8/22.
 */
public class ClientContent {
    private String[] content;
    private int length;
    private int currentIndex;
    public String[] getContent() {
        return content;
    }
    public void setContent(String[] content) {
        this.content = content;
    }
    public String[] addContent(String content){
        if(this.content==null){
            this.content= new String[length];
            this.content[0]=content;
        }else{
            this.content[currentIndex]=content;
        }
        return this.content;
    }

    public ClientContent(int length) {
        this.length = length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
