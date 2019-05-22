package org.terence.backend.common.utils.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.terence.backend.dao.entity.admin.Group;
import org.terence.backend.dao.entity.admin.Menu;
import org.terence.backend.dao.entity.admin.User;
import org.terence.backend.service.vo.admin.GroupVo;
import org.terence.backend.service.vo.admin.MenuVo;
import org.terence.backend.service.vo.admin.UserVo;

import java.util.Optional;

/**
 * @author terence
 * @since 2019/2/28 16:55
 */
public class BeanFormat {

    public static MapperFactory formatUser() {
        MapperFactory factory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        ClassMapBuilder<User, User> builder = factory.classMap(User.class, User.class);
        builder.byDefault().register();
        return factory;
    }

    public static UserVo formatUserWithoutPwd(User user) {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        ClassMapBuilder<User, UserVo> builder = factory.classMap(User.class, UserVo.class);
        builder
                .field("id", "userId")
                .field("username", "username")
                .field("name", "name")
                .field("email", "email")
                .field("gender", "gender")
                .field("mobile", "mobile")
                .field("description", "description")
                .field("group.id", "groupId")
                .field("group.name", "groupName");
        builder.register();
        return factory.getMapperFacade().map(user, UserVo.class);
    }

    public static MapperFactory formatUserAndUserVo() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        ClassMapBuilder<UserVo, User> builder = factory.classMap(UserVo.class, User.class);
        builder
                .field("userId", "id")
                .field("groupId", "group.id")
                .field("groupName", "group.name");
        builder.byDefault().register();
        return factory;
    }

    public static MapperFactory formatGroupVo() {
        MapperFactory factory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        ClassMapBuilder<Group, GroupVo> builder = factory.classMap(Group.class, GroupVo.class);
        builder.field("id", "groupId");
        builder.byDefault().register();
        return factory;
    }

    public static MapperFactory formatMenuVo() {
        MapperFactory factory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        ClassMapBuilder<Menu, MenuVo> builder = factory.classMap(Menu.class, MenuVo.class);
        builder.byDefault().register();
        return factory;
    }
}
