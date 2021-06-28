package com.liangzc.example.design_pattern.memo;

import java.util.Stack;

public class Drafts {

    private static final Stack<MemoRandum> MemoRandums = new Stack<>();


    public void addDrafts(MemoRandum memoRandum){
        MemoRandums.push(memoRandum);
    }


    public MemoRandum undoDrafts(){
        MemoRandum pop = MemoRandums.pop();
        return pop;
    }
}
