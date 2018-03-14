package pers.mao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mao.dao.AdminDao;
import pers.mao.pojo.Admin;
import pers.mao.pojo.AdminExample;
import pers.mao.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public int getAdminCount(Admin admin) {
        AdminExample example = new AdminExample();
        example.createCriteria().andAdminNameEqualTo(admin.getAdminName()).andAdminPasswordEqualTo(admin.getAdminPassword());
        return adminDao.countByExample(example);
    }
}
