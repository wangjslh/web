package com.jslh.dao.mapper;

import com.jslh.model.SysRole;
import com.jslh.model.SysRoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	int countByExample(SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	int deleteByExample(SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	int insert(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	int insertSelective(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	List<SysRole> selectByExample(SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	SysRole selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	int updateByExampleSelective(@Param("record") SysRole record,
			@Param("example") SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	int updateByExample(@Param("record") SysRole record,
			@Param("example") SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	int updateByPrimaryKeySelective(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Thu Mar 05 16:54:23 CST 2015
	 */
	int updateByPrimaryKey(SysRole record);
}