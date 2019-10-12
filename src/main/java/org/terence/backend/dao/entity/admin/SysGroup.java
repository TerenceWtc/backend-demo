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
import java.util.List;

/**
 * @author terence
 * @since 2019/2/20 10:15
 */
@Data
@ToString(exclude = {"sysMenus"})
@Entity(name = "sys_group")
@EntityListeners(AuditingEntityListener.class)
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

    @OneToMany(mappedBy = "sysGroup", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<SysUser> sysUser;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "sys_group_menu", joinColumns = { @JoinColumn(name = "sys_group_id") },
    inverseJoinColumns = { @JoinColumn(name = "sys_menu_id") })
    @JsonBackReference
    private List<SysMenu> sysMenus;
}
