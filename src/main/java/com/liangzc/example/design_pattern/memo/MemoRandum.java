package com.liangzc.example.design_pattern.memo;

public class MemoRandum {

    private String title;

    private String content;

    private String address;

    public MemoRandum(String title, String content, String address) {
        this.title = title;
        this.content = content;
        this.address = address;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAddress() {
        return address;
    }


    @Override
    public String toString() {
        return "MemoRandum{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
