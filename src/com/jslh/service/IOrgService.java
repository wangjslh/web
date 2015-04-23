package com.jslh.service;

import java.util.List;

import com.jslh.model.SysOrg;
import com.jslh.model.SysOrgExample;

public interface IOrgService {

    int deleteByExample(SysOrgExample example);

    int deleteByPrimaryKey(Long id);

    void insertSysOrg(String name, String parentPath, String description);

    List<SysOrg> selectByExample(SysOrgExample example);

    SysOrg selectByPrimaryKey(Long id);

    int updateByExampleSelective(SysOrg record, SysOrgExample example);

    int updateByExample(SysOrg record, SysOrgExample example);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);
    
    List<SysOrg> selectDescendants(String path);
    
    List<SysOrg> selectChildren(String path);
}
