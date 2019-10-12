package org.terence.backend.dao.entity.admin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author terence
 * @since 2019/2/18 17:27
 */
@Data
@ToString(exclude = "sysGroup")
@Entity(name = "sys_user")
@EntityListeners(AuditingEntityListener.class)
public class SysUser implements Serializable {

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

    @ManyToOne
    @JoinColumn(name = "gender_id")
    @JsonBackReference
    private SysDictionary gender;

    private String mobile;

    private String description;

    @Column(nullable = false)
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    @Column(nullable = false)
    @CreatedBy
    private String createBy;

    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    @LastModifiedBy
    private String updateBy;

    @ManyToOne
    @JoinColumn(name = "sys_group_id")
    @JsonBackReference
    private SysGroup sysGroup;

    public SysUser() {
    }

    // TODO: to be delete
    public SysUser(String username, String password, String name, String email, Date createTime, String createBy) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createTime = createTime;
        this.createBy = createBy;
    }

    // constructor for refresh token
    public SysUser(long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

//    // constructor for register / add
//    public SysUser(String username, String password, String name, String email, String gender, String mobile, SysGroup sysGroup) {
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.email = email;
//        this.gender = gender;
//        this.mobile = mobile;
//        this.sysGroup = sysGroup;
//    }
//
//    // constructor for detail
//    public SysUser(String username, String name, String email, String gender, String mobile, SysGroup sysGroup) {
//        this.username = username;
//        this.name = name;
//        this.email = email;
//        this.gender = gender;
//        this.mobile = mobile;
//        this.sysGroup.setId(sysGroup.getId());
//    }
}
