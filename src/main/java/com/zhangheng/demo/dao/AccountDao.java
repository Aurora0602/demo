package com.zhangheng.demo.dao;

import com.zhangheng.demo.entity.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * (Account)表数据库访问层
 *
 * @author zhangheng
 * @since 2022-03-24 14:14:25
 */
@Mapper
public interface AccountDao {

    @Select("select * from account where account_name = #{account_name} and account_password = #{account_password}")//#{}里面是实体类的属性
    @Results({
            @Result(column = "account_id", property = "account_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "account_name", property = "account_name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "account_password", property = "account_password", jdbcType = JdbcType.VARCHAR)
    })
    Account checkAccount(Account account);

    /**
     * 通过ID查询单条数据
     *
     * @param account_id 主键
     * @return 实例对象
     */
    @Select("select * from account where account_id = #{account_id}")
    Account queryByAccount_id(Integer account_id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Account> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param account 实例对象
     * @return 对象列表
     */
    List<Account> queryAll(Account account);

    /**
     * 统计总行数
     *
     * @param account 查询条件
     * @return 总行数
     */
    long count(Account account);

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    int insert(Account account);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Account> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Account> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Account> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Account> entities);

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    int update(Account account);

    /**
     * 通过主键删除数据
     *
     * @param accountId 主键
     * @return 影响行数
     */
    int deleteById(Integer accountId);

}

