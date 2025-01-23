package com.liangzc.example.design_pattern.memo;

public class Test {

    public static void main(String[] args) {

        Drafts drafts = new Drafts();
        Edit edit = new Edit("课程", "java学习", "localhost/java_study.com");
        MemoRandum memoRandum = edit.addMemoRandum();
        drafts.addDrafts(memoRandum);
        System.out.println(edit);

        System.out.println("=====================修改后=========================");
        edit = new Edit("课程", "python学习", "localhost/java_study.com");
        memoRandum = edit.addMemoRandum();
        drafts.addDrafts(memoRandum);
        System.out.println(edit);

        System.out.println("=========================第一次撤销==============================");
        memoRandum = drafts.undoDrafts();
        edit.undoMemoRandum(memoRandum);
        System.out.println(edit);
        System.out.println("=========================第一次撤销完成==============================");

        System.out.println("=========================第二次撤销==============================");
        memoRandum = drafts.undoDrafts();
        edit.undoMemoRandum(memoRandum);
        System.out.println(edit);
        System.out.println("=========================第二次撤销完成==============================");
    }
}
