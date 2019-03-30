package org.terence.backend.dao.entity.admin;

import lombok.Data;
import lombok.ToString;
import org.terence.backend.common.constant.CommonConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author terence
 * @since 2019/2/22 17:06
 */
@Data
@ToString(exclude = "group")
@Entity(name = "base_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = -3917945599306780941L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String code;

    private String title;

    private int parentId = CommonConstant.ROOT;

//    @Column(nullable = false)
    private String href;

    private String icon;

    private String type;

    private String description;

    @Column(nullable = false, columnDefinition = "datetime default now()")
    private Date createTime;

    @Column(nullable = false)
    private String createBy;

    @ManyToMany(mappedBy = "menu")
    private List<Group> group;
}
