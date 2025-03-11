package org.wms.sys.controller.api;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.entity.sys.MenuTree;
import org.wms.sys.service.MenuService;

import java.util.List;

/**
 * 暴露菜单接口用于RPC远程调用
 */
@RestController
@RequestMapping("/api/menu")
public class MenuApiController {

    private static final Logger log = LoggerFactory.getLogger(MenuApiController.class);
    @Resource
    MenuService menuService;

    /**
     * 根据用户ID查询出当前用户的树状菜单列表
     * @param userId 用户id
     * @return List
     */
    @GetMapping("/tree/{userId}")
    @Cacheable(value = "cache::menuTree", key = "#userId")
    public List<MenuTree> getMenuTree(@PathVariable String userId) {
        log.info("getMenuTree调用开始,userId:{}", userId);
        return menuService.getTreeMenu(userId);
    }
}
