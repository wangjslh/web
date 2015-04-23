package com.jslh.dao.mapperext;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jslh.model.SysOrg;

@Repository
public interface SysOrgExtMapper {
    
	List<SysOrg> selectDescendants(String path);
	
	List<SysOrg> selectChildren(@Param("path") String path, @Param("pathLength") int pathLength);
	
}