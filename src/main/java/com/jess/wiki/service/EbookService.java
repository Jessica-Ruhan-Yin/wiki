package com.jess.wiki.service;

import com.jess.wiki.domain.Demo;
import com.jess.wiki.domain.Ebook;
import com.jess.wiki.mapper.DemoMapper;
import com.jess.wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

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

    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list(){
        return ebookMapper.selectByExample(null);
    }

}
