package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wms.api.client.fallback.MenuClientFallback;
import org.wms.common.entity.MenuTree;

import java.util.List;

/**
 * 菜单远程调用接口
 * 想要触发兜底必须引入sentinel并在yaml中配置
 */
@FeignClient(value = "sys-service", contextId = "sys.menu", fallbackFactory = MenuClientFallback.class)
public interface MenuClient {
    /**
     * 根据用户ID查询出当前用户的树状菜单列表
     *
     * @param userId 用户id
     * @return List
     */
    @GetMapping("/api/menu/tree/{userId}")
    List<MenuTree> getMenuTree(@PathVariable String userId);
}
