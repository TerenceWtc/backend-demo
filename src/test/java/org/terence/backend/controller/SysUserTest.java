package org.terence.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.entity.admin.SysUser;
import org.terence.backend.dao.repository.admin.SysGroupRepository;
import org.terence.backend.dao.repository.admin.SysUserRepository;
import org.terence.backend.dao.specification.admin.SysGroupSpec;

import java.util.List;
import java.util.Optional;

/**
 * @Author: terence
 * @Date: 2019/2/21 16:09
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SysUserTest {

    @Autowired
    private SysGroupRepository sysGroupRepository;

    @Autowired
    private SysUserRepository sysUserRepository;

//    @Test
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
//            Join<GroupUser, SysGroup> joinGroup = root.join("sysGroup", JoinType.LEFT);
//            predicateList.add(builder.equal(root.get("sysGroup").get("id"), joinGroup.get("id")));
//            predicateList.add(builder.le(root.get("sysUser").get("id"), 3L));
//            Predicate[] predicates = new Predicate[predicateList.size()];
//            query.where(predicateList.toArray(predicates));
//            return builder.and(predicateList.toArray(predicates));
//        });
//        list.forEach(System.out::println);
    }

//    @Test
    public void test2() {
        long userId = 8L;
        String username = "admin";
        List<SysGroup> sysGroup = sysGroupRepository.findAll(SysGroupSpec.findOneByUsername(username));
//        if (sysGroup.isPresent()) {
//            System.out.println(sysGroup.get())
//        }

        sysGroup.forEach(item -> System.out.println(item.getId()));
        sysGroup.forEach(System.out::println);
    }

//    @Test
    public void authorization() {
        long userId = 8L;
        long groupId = 1L;
        Optional<SysUser> userOptional = sysUserRepository.findById(userId);
        Optional<SysGroup> groupOptional = sysGroupRepository.findById(groupId);
        if (userOptional.isPresent() && groupOptional.isPresent()) {
            SysUser sysUser = userOptional.get();
            sysUser.setSysGroup(groupOptional.get());
            sysUserRepository.save(sysUser);
        }
    }
}
