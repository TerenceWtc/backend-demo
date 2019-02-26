package org.terence.backend.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.terence.backend.dao.entity.admin.Group;
import org.terence.backend.dao.entity.admin.User;
import org.terence.backend.dao.repository.admin.GroupRepository;
import org.terence.backend.dao.repository.admin.UserRepository;
import org.terence.backend.dao.repository.admin.specification.GroupSpec;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: terence
 * @Date: 2019/2/21 16:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        /**
         * equals to following SQL:
         * Select base_group_user.*
         * From base_group_user
         * Left join base_group on (base_group_user.group_id = base_group.id)
         * Left join base_user on (base_group_user.user_id = base_user.id)
         * Where base_user.id <= 3
         */
//        List<GroupUser> list = groupUserRepository.findAll((Root<GroupUser> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
//            List<Predicate> predicateList = new ArrayList<>();
//            Join<GroupUser, Group> joinGroup = root.join("group", JoinType.LEFT);
//            predicateList.add(builder.equal(root.get("group").get("id"), joinGroup.get("id")));
//            predicateList.add(builder.le(root.get("user").get("id"), 3L));
//            Predicate[] predicates = new Predicate[predicateList.size()];
//            query.where(predicateList.toArray(predicates));
//            return builder.and(predicateList.toArray(predicates));
//        });
//        list.forEach(System.out::println);
    }

    @Test
    public void test2() {
        long userId = 1L;
        String username = "admin";
        List<Group> group = groupRepository.findAll(GroupSpec.findOneByUsername(username));
//        if (group.isPresent()) {
//            System.out.println(group.get())
//        }

        group.forEach(item -> System.out.println(item.getId()));
        group.forEach(System.out::println);
    }

    @Test
    public void test3() {
    }
}
