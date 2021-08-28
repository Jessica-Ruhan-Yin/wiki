package com.jess.wiki.service;

import com.jess.wiki.domain.Demo;
import com.jess.wiki.domain.Test;
import com.jess.wiki.mapper.DemoMapper;
import com.jess.wiki.mapper.TestMapper;
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
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list(){
        return demoMapper.selectByExample(null);
    }

}
