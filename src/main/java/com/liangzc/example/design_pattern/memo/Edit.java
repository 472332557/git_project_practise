package com.liangzc.example.design_pattern.memo;

public class Edit {

    private String title;

    private String content;

    private String address;

    public Edit(String title, String content, String address) {
        this.title = title;
        this.content = content;
        this.address = address;
    }

    public MemoRandum addMemoRandum(){
        MemoRandum memoRandum = new MemoRandum(this.title,this.content,this.address);
        return memoRandum;

    }

    public void undoMemoRandum(MemoRandum memoRandum){

        this.title = memoRandum.getTitle();
        this.content = memoRandum.getContent();
        this.address = memoRandum.getAddress();
    }


    @Override
    public String toString() {
        return "Edit{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
