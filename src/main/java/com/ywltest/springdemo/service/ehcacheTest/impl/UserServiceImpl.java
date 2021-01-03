package com.ywltest.springdemo.service.ehcacheTest.impl;

import com.ywltest.springdemo.domain.model.User;
import com.ywltest.springdemo.service.ehcacheTest.UserService;
import com.ywltest.springdemo.util.EhCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 * @author yangWenlong
 * @date 2020/12/1- ${time}
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {


}

