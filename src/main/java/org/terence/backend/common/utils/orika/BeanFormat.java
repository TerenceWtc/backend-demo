package org.terence.backend.common.utils.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.terence.backend.dao.entity.admin.User;
import org.terence.backend.service.vo.admin.UserVo;

/**
 * @author terence
 * @since 2019/2/28 16:55
 */
public class BeanFormat {

    public static UserVo formatUser(User user) {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        ClassMapBuilder<User, UserVo> builder = factory.classMap(User.class, UserVo.class);
        builder
                .field("id", "userId")
                .field("group.id", "groupId")
                .field("group.name", "groupName");
        builder.byDefault().register();
        return factory.getMapperFacade().map(user, UserVo.class);
    }
}
