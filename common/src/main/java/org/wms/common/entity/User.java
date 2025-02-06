package org.wms.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("sys_user")
@Data
public class User implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String userId;

    private String wxId;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String nickName;

    private Integer sex;

    private String avatar;

    private Date createTime;

    private Date updateTime;

}