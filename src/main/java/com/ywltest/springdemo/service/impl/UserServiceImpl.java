//package com.ywltest.springdemo.service.impl;
//
//import com.ywltest.springdemo.domain.model.User;
//import com.ywltest.springdemo.service.UserService;
//import com.ywltest.springdemo.util.EhCacheUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import com.google.common.collect.Maps;
//import java.util.Map;
//
///**
// * @author yangWenlong
// * @date 2020/12/1- ${time}
// */
//@Service
//@Slf4j
//public class UserServiceImpl implements UserService {
//
//    /**
//     * 模拟数据库
//     */
//    private static final Map<Long, User> DATABASES = Maps.newConcurrentMap();
//
//    /**
//     * 初始化数据
//     */
//    static {
//        DATABASES.put(1L, new User(1L, "user1"));
//        DATABASES.put(2L, new User(2L, "user2"));
//        DATABASES.put(3L, new User(3L, "user3"));
//    }
//
//    /**
//     * 保存或修改用户
//     *
//     * @param user 用户对象
//     * @return 操作结果
//     */
//    @CachePut(value = "user", key = "#user.id")
//    @Override
//    public User saveOrUpdate(User user) {
//        DATABASES.put(user.getId(), user);
//        log.info("保存用户【user】= {}", user);
//
//
//        EhCacheUtil ehCacheUtil = EhCacheUtil.getInstance();
//        ehCacheUtil.put(EhCacheUtil.TAXI_CACHE, EhCacheUtil.GROUP_VEHICLE_TREE_KEY,user);
//        User user1 = (User) ehCacheUtil.get(EhCacheUtil.TAXI_CACHE, EhCacheUtil.GROUP_VEHICLE_TREE_KEY);
//        log.info("\n =====>【{}】",user1);
//        return user;
//    }
//
//    /**
//     * 获取用户
//     *
//     * @param id key值
//     * @return 返回结果
//     */
//    @Cacheable(value = "user", key = "#id")
//    @Override
//    public User get(Long id) {
//        // 我们假设从数据库读取
//        log.info("查询用户【id】= {}", id);
//        return DATABASES.get(id);
//    }
//
//    /**
//     * 删除
//     *
//     * @param id key值
//     */
//    @CacheEvict(value = "user", key = "#id")
//    @Override
//    public void delete(Long id) {
//        DATABASES.remove(id);
//        log.info("删除用户【id】= {}", id);
//    }
//}
//
