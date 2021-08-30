package com.jess.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jess.wiki.domain.Ebook;
import com.jess.wiki.domain.EbookExample;
import com.jess.wiki.mapper.EbookMapper;
import com.jess.wiki.req.EbookReq;
import com.jess.wiki.resp.EbookResp;
import com.jess.wiki.resp.PageResp;
import com.jess.wiki.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/8/28
 */
@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    public PageResp<EbookResp> list(EbookReq req) {

        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }

        PageHelper.startPage(req.getPage(), req.getSize());//支持分页
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

//        List<EbookResp> respList = new ArrayList<>();
//        for (Ebook ebook : ebookList) {
//            //单个复制
//            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
//            respList.add(ebookResp);
//        }


        //工具类列表复制代替循环
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);
        PageResp<EbookResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

}
