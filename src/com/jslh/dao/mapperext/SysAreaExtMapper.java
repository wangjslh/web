package com.jslh.dao.mapperext;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jslh.model.SysArea;

@Repository
public interface SysAreaExtMapper {
    
	List<SysArea> selectDescendants(String code);
	
	List<SysArea> selectChildren(@Param("code") String code, @Param("codeLength") int codeLength);
	
}