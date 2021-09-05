package com.jess.wiki.resp;

import java.util.Date;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/9/5
 */
public class StatisticResp {
    private Date date;

    private int view_count;

    private int vote_count;

    private int viewIncrease;

    private int voteIncrease;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getViewIncrease() {
        return viewIncrease;
    }

    public void setViewIncrease(int viewIncrease) {
        this.viewIncrease = viewIncrease;
    }

    public int getVoteIncrease() {
        return voteIncrease;
    }

    public void setVoteIncrease(int voteIncrease) {
        this.voteIncrease = voteIncrease;
    }

    @Override
    public String toString() {
        return "StatisticResp{" +
                "date=" + date +
                ", view_count=" + view_count +
                ", vote_count=" + vote_count +
                ", viewIncrease=" + viewIncrease +
                ", voteIncrease=" + voteIncrease +
                '}';
    }
}
