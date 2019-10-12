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
@ToString(exclude = "sysGroup")
@Entity(name = "sys_menu")
@EntityListeners(AuditingEntityListener.class)
public class SysMenu implements Serializable {

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

    @ManyToMany(mappedBy = "sysMenus")
    @JsonBackReference
    private List<SysGroup> sysGroup;
}
