package com.ywltest.springdemo.util.common;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;


public class LogicalPageUtils<T> {

    /**
     * 获取分页数据
     *
     * @param data 总数据
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return 返回结果
     */
    public List<T> subList(List<T> data, Integer pageNum, Integer pageSize) {
        if (CollectionUtil.isEmpty(data)) {
            return new ArrayList<>();
        }

        // 总数据量
        int dataSize = data.size();

        // 页大小大于等于总数据量
        if (pageSize >= dataSize) {
            return data;
        }

        // 开始索引
        int fromIndex = (pageNum - 1) * pageSize;
        // 结束索引
        int toIndex = fromIndex + (dataSize > pageSize * pageNum  ? pageSize : (dataSize % pageSize));

        return data.subList(fromIndex, toIndex);
    }

}
