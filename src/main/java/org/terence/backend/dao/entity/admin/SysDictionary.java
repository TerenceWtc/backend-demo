package org.terence.backend.dao.entity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.terence.backend.common.constant.CommonConstant;

import javax.persistence.*;
import java.util.Date;

/**
 * @author terence
 * @since 2019/7/24 17:18
 */
@Data
@Entity(name = "sys_dictionary")
public class SysDictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    private String description;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private long parentId = CommonConstant.ROOT;

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
}
