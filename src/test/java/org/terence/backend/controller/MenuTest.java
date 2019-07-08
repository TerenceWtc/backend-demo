package org.terence.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.entity.admin.SysMenu;
import org.terence.backend.dao.repository.admin.GroupRepository;
import org.terence.backend.dao.repository.admin.MenuRepository;
import org.terence.backend.dao.repository.admin.specification.MenuSpec;

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
public class MenuTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MenuRepository menuRepository;

//    @Test
    public void authorization() {
//        List<SysMenu> sysMenuList = menuRepository.findAll();
//
//        Optional<SysGroup> sysGroup = groupRepository.findById(1L);
//        sysGroup.ifPresent(group1 -> {
//            group1.setSysMenus(sysMenuList);
//            groupRepository.save(group1);
//        });

        Optional<SysMenu> menu = menuRepository.findById(3L);
        List<SysMenu> sysMenuList = new ArrayList<>();
        menu.ifPresent(sysMenuList::add);
        Optional<SysGroup> group = groupRepository.findById(-1L);
        group.ifPresent(group1 -> {
            group1.setSysMenus(sysMenuList);
            groupRepository.save(group1);
        });


    }

//    @Test
    public void list() {
        long groupId = 1L;
        List<SysMenu> sysMenuList = menuRepository.findAll(MenuSpec.findAllByGroupId(groupId));

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

        sysMenuList.forEach(item -> menuRepository.save(item));
    }

}
