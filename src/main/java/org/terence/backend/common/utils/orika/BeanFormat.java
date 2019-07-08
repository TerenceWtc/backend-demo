package org.terence.backend.common.utils.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.entity.admin.SysMenu;
import org.terence.backend.dao.entity.admin.SysUser;
import org.terence.backend.service.vo.admin.GroupVo;
import org.terence.backend.service.vo.admin.MenuVo;
import org.terence.backend.service.vo.admin.UserVo;

/**
 * @author terence
 * @since 2019/2/28 16:55
 */
public class BeanFormat {

    public static MapperFactory formatUser() {
        MapperFactory factory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        ClassMapBuilder<SysUser, SysUser> builder = factory.classMap(SysUser.class, SysUser.class);
        builder.byDefault().register();
        return factory;
    }

    public static UserVo formatUserWithoutPwd(SysUser sysUser) {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        ClassMapBuilder<SysUser, UserVo> builder = factory.classMap(SysUser.class, UserVo.class);
        builder
                .field("id", "userId")
                .field("username", "username")
                .field("name", "name")
                .field("email", "email")
                .field("gender", "gender")
                .field("mobile", "mobile")
                .field("description", "description")
                .field("sysGroup.id", "groupId")
                .field("sysGroup.name", "groupName");
        builder.register();
        return factory.getMapperFacade().map(sysUser, UserVo.class);
    }

    public static MapperFactory formatUserAndUserVo() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        ClassMapBuilder<UserVo, SysUser> builder = factory.classMap(UserVo.class, SysUser.class);
        builder
                .field("userId", "id")
                .field("groupId", "sysGroup.id")
                .field("groupName", "sysGroup.name");
        builder.byDefault().register();
        return factory;
    }

    public static MapperFactory formatGroupVo() {
        MapperFactory factory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        ClassMapBuilder<SysGroup, GroupVo> builder = factory.classMap(SysGroup.class, GroupVo.class);
        builder.field("id", "groupId");
        builder.byDefault().register();
        return factory;
    }

    public static MapperFactory formatMenuVo() {
        MapperFactory factory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        ClassMapBuilder<SysMenu, MenuVo> builder = factory.classMap(SysMenu.class, MenuVo.class);
        builder.byDefault().register();
        return factory;
    }
}
