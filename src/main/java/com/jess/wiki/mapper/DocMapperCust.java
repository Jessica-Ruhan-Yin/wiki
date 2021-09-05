package com.jess.wiki.mapper;

import com.jess.wiki.domain.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/8/28
 */
public interface DocMapperCust {

    public void increaseViewCount(@Param("id") Long id);
}
