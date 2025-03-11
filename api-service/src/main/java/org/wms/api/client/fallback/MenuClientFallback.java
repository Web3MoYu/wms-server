package org.wms.api.client.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.wms.api.client.MenuClient;
import org.wms.common.entity.sys.MenuTree;

import java.util.List;

/**
 * MenuClient 兜底策略
 */
public class MenuClientFallback implements FallbackFactory<MenuClient> {

    private static final Logger log = LoggerFactory.getLogger(MenuClientFallback.class);

    @Override
    public MenuClient create(Throwable cause) {
        return new MenuClient() {
            @Override
            public List<MenuTree> getMenuTree(String userId) {
                log.warn("触发兜底,方法:getMenuTree, userId:{}", userId);
                return List.of();
            }
        };
    }
}
