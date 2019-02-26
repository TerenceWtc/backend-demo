package org.terence.backend.dao.entity.admin;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author terence
 * @since 2019/2/18 17:27
 */
@Data
@ToString(exclude = "group")
@Entity(name = "base_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String gender;

    private String mobile;

    private String description;

    @Column(nullable = false, columnDefinition = "datetime default now()")
    private Date createTime;

    @Column(nullable = false)
    private String createBy;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
