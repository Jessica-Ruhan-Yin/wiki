package com.jess.wiki.mapper;

import com.jess.wiki.resp.StatisticResp;

import java.util.List;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/9/5
 */
public interface EbookSnapshotMapperCust {
    public void getSnapshot();

    List<StatisticResp> getStatistic();
}
