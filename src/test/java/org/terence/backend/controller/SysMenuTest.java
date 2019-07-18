package org.terence.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.entity.admin.SysMenu;
import org.terence.backend.dao.repository.admin.SysGroupRepository;
import org.terence.backend.dao.repository.admin.SysMenuRepository;
import org.terence.backend.dao.specification.admin.SysMenuSpec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: terence
 * @Date: 2019/2/25 10:42
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SysMenuTest {

    @Autowired
    private SysGroupRepository sysGroupRepository;

    @Autowired
    private SysMenuRepository sysMenuRepository;

//    @Test
    public void authorization() {
//        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
//
//        Optional<SysGroup> sysGroup = sysGroupRepository.findById(1L);
//        sysGroup.ifPresent(group1 -> {
//            group1.setSysMenus(sysMenuList);
//            sysGroupRepository.save(group1);
//        });

        Optional<SysMenu> menu = sysMenuRepository.findById(3L);
        List<SysMenu> sysMenuList = new ArrayList<>();
        menu.ifPresent(sysMenuList::add);
        Optional<SysGroup> group = sysGroupRepository.findById(-1L);
        group.ifPresent(group1 -> {
            group1.setSysMenus(sysMenuList);
            sysGroupRepository.save(group1);
        });


    }

//    @Test
    public void list() {
        long groupId = 1L;
        List<SysMenu> sysMenuList = sysMenuRepository.findAll(SysMenuSpec.findAllByGroupId(groupId));

        sysMenuList.forEach(System.out::println);
    }

//    @Test
    public void addMenu() {
        List<SysMenu> sysMenuList = new ArrayList<>();
        SysMenu sysMenu = new SysMenu();

        sysMenu.setCode("introduction/index");
        sysMenu.setTitle("introduction");
        sysMenu.setIcon("introduction");
        sysMenu.setParentId(-1);
        sysMenu.setCreateTime(new Date());
        sysMenu.setCreateBy("DBA");
        sysMenuList.add(sysMenu);

        sysMenu.setCode("introduction/index");
        sysMenu.setTitle("introduction");
        sysMenu.setIcon("introduction");
        sysMenu.setParentId(-1);
        sysMenu.setCreateTime(new Date());
        sysMenu.setCreateBy("DBA");
        sysMenuList.add(sysMenu);
//
//        sysMenu = new SysMenu();
//        sysMenu.setCode("demoes/checkbox");
//        sysMenu.setTitle("checkbox");
//        sysMenu.setIcon("validation");
//        sysMenu.setParentId(-1);
//        sysMenu.setCreateTime(new Date());
//        sysMenu.setCreateBy("DBA");
//        sysMenuList.add(sysMenu);

//        sysMenu.setCode("demoes/formValidation");
//        sysMenu.setTitle("formValidation");
//        sysMenu.setIcon("validation");
//        sysMenu.setParentId(-1);
//        sysMenu.setCreateTime(new Date());
//        sysMenu.setCreateBy("DBA");
//        sysMenuList.add(sysMenu);
//
//        sysMenu = new SysMenu();
//        sysMenu.setCode("demoes/line");
//        sysMenu.setTitle("line");
//        sysMenu.setIcon("chart");
//        sysMenu.setParentId(-1);
//        sysMenu.setCreateTime(new Date());
//        sysMenu.setCreateBy("DBA");
//        sysMenuList.add(sysMenu);

        sysMenuList.forEach(item -> sysMenuRepository.save(item));
    }

}
