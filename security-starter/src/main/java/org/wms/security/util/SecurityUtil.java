package org.wms.security.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.wms.common.model.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringSecurity工具类
 */
public class SecurityUtil {
    public static String getUserID() {
        UserDetails userDetail = getUserDetail();
        return userDetail.getID();
    }

    public static UserDetails getUserDetail() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static List<GrantedAuthority> convertStringToGrantedAuthority(List<String> auths) {
        ArrayList<GrantedAuthority> result = new ArrayList<>();
        for (String auth : auths) {
            result.add(new SimpleGrantedAuthority(auth));
        }
        return result;
    }
}
