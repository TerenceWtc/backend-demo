package org.terence.backend.dao.entity.admin;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author terence
 * @since 2019/2/20 10:15
 */
@Data
@ToString(exclude = "user")
@Entity(name = "base_group")
public class Group {

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

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<User> user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "base_group_menu", joinColumns = { @JoinColumn(name = "group_id") },
    inverseJoinColumns = { @JoinColumn(name = "menu_id") })
    private List<Menu> menu;
}
