package org.wms.auth.model.vo;

import lombok.Data;
import org.wms.common.entity.sys.MenuTree;
import org.wms.common.entity.sys.User;

import java.util.List;

/**
 * 登录成功响应结果
 */
@Data
public class LoginVo {

    /**
     * 用户
     */
    User user;
    /**
     * 菜单树
     */
    List<MenuTree> menuTree;
    /**
     * 令牌
     */
    String token;
}
