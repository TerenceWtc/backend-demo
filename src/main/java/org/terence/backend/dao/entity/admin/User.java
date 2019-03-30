package org.terence.backend.dao.entity.admin;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author terence
 * @since 2019/2/18 17:27
 */
@Data
@ToString(exclude = "group")
@Entity(name = "base_user")
public class User implements Serializable {

    private static final long serialVersionUID = -2308494806205517973L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

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

    public User() {
    }

    public User(String username, String password, String name, String email, Date createTime, String createBy) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createTime = createTime;
        this.createBy = createBy;
    }

    public User(long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
}
