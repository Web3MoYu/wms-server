package org.wms.auth.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WxLoginVo {

    Boolean binding;
    LoginVo userInfo;
}
