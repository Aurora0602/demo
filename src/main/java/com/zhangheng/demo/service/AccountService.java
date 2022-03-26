package com.zhangheng.demo.service;

import com.zhangheng.demo.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;

/**
 * (Account)表服务接口
 *
 * @author zhangheng
 * @since 2022-03-24 14:14:25
 */

public interface AccountService {

    Account checkAccount(Account account);

    /**
     * 通过ID查询单条数据
     *
     * @param accountId 主键
     * @return 实例对象
     */
    Account queryById(Integer accountId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Account> queryAllByLimit(int offset, int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param account 实例对象
     * @return 对象列表
     */
    List<Account> queryAll(Account account);


    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    Account insert(Account account);

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    Account update(Account account);

    /**
     * 通过主键删除数据
     *
     * @param accountId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer accountId);

}
