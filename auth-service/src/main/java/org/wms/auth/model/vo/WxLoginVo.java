package org.wms.auth.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WxLoginVo {

    /**
     * 是否绑定
     */
    Boolean binding;
    /**
     * 用户信息
     */
    LoginVo userInfo;
}
