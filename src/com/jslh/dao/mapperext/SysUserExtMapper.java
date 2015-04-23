package com.jslh.dao.mapperext;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jslh.model.SysUser;
import com.jslh.model.ext.SysUserExt;

@Repository
public interface SysUserExtMapper {

	List<SysUserExt> selectUserExt(@Param("user") SysUser user, @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);
	
	int countUserExt(@Param("user") SysUser user, @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);
}
