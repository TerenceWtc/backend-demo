package org.terence.backend.dao.entity.admin;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author terence
 * @since 2019/2/20 10:15
 */
@Data
@ToString(exclude = {"sysUser", "sysMenus"})
@Entity(name = "sys_group")
public class SysGroup implements Serializable {

    private static final long serialVersionUID = -3751135703095273361L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false, columnDefinition = "datetime default now()")
    private Date createTime;

    @Column(nullable = false)
    private String createBy;

    @OneToMany(mappedBy = "sysGroup", fetch = FetchType.EAGER)
    private List<SysUser> sysUser;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "sys_group_menu", joinColumns = { @JoinColumn(name = "sys_group_id") },
    inverseJoinColumns = { @JoinColumn(name = "sys_menu_id") })
    private List<SysMenu> sysMenus;
}
