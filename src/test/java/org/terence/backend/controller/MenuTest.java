package org.terence.backend.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.terence.backend.dao.entity.admin.Group;
import org.terence.backend.dao.entity.admin.Menu;
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
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void test() {
//        List<Menu> menuList = new ArrayList<>();
//        Menu menu = new Menu();
//
//        menu.setCode("home");
//        menu.setTitle("home page");
//        menu.setParentId(-1);
//        menu.setCreateTime(new Date());
//        menu.setCreateBy("DBA");
//        menuList.add(menu);
//
//        menu = new Menu();
//        menu.setCode("home2");
//        menu.setTitle("home2 page");
//        menu.setParentId(-1);
//        menu.setCreateTime(new Date());
//        menu.setCreateBy("DBA");
//        menuList.add(menu);

        List<Menu> menuList = menuRepository.findAll();

        Optional<Group> group = groupRepository.findById(1L);
        group.ifPresent(group1 -> {
            group1.setMenu(menuList);
            groupRepository.save(group1);
        });
    }

    @Test
    public void test2() {
        long groupId = 1L;
        List<Menu> menuList = menuRepository.findAll(MenuSpec.findAllByGroupId(groupId));

        menuList.forEach(System.out::println);
    }
}
