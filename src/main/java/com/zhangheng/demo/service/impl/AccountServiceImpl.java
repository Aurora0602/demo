package com.zhangheng.demo.service.impl;

import com.zhangheng.demo.entity.Account;
import com.zhangheng.demo.dao.AccountDao;
import com.zhangheng.demo.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Account)表服务实现类
 *
 * @author zhangheng
 * @since 2022-03-24 14:14:25
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public Account checkAccount(Account account) {
        return this.accountDao.checkAccount(account);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param accountId 主键
     * @return 实例对象
     */
    @Override
    public Account queryById(Integer accountId) {
        return this.accountDao.queryById(accountId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Account> queryAllByLimit(int offset, int limit) {
        return accountDao.queryAllByLimit(offset, limit);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param account 实例对象
     * @return 对象列表
     */
    public List<Account> queryAll(Account account){
        return accountDao.queryAll(account);
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Override
    public Account insert(Account account) {
        this.accountDao.insert(account);
        return account;
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Override
    public Account update(Account account) {
        this.accountDao.update(account);
        return this.queryById(account.getAccount_id());
    }

    /**
     * 通过主键删除数据
     *
     * @param accountId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer accountId) {
        return this.accountDao.deleteById(accountId) > 0;
    }
}
