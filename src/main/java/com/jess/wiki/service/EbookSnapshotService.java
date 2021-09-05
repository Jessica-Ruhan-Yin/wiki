package com.jess.wiki.service;

import com.jess.wiki.mapper.EbookSnapshotMapperCust;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/9/5
 */


@Service
public class EbookSnapshotService {

    @Resource
    private EbookSnapshotMapperCust ebookSnapshotMapperCust;

    public void genSnapshot() {
        ebookSnapshotMapperCust.genSnapshot();
    }

}
