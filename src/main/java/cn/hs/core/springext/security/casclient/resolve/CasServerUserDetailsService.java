/*
 * $Log: CasServerUserDetailsService.java,v $
 * Revision 1.1  2012/05/23 09:27:50  guor
 * 初次提交
 *
 */
package cn.hs.core.springext.security.casclient.resolve;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.hs.commons.utils.JSONUtils;
import cn.hs.core.springext.security.casclient.domain.json.CasRole;
import cn.hs.core.springext.security.casclient.domain.json.CasSystems;
import cn.hs.core.springext.security.casclient.domain.json.CasUser;
import cn.hs.module.role.domain.RoleResourceCondition;
import cn.hs.module.role.domain.querybena.RoleResourceMenuBean;
import cn.hs.module.role.service.IRoleResourceService;

/**
 * Title: CasServerUserDetailsService<br>
 * Description: Cas服务端返回值解析<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Sep 6, 2011
 * @version $Revision: 1.1 $
 */
public class CasServerUserDetailsService implements UserDetailsService {
    @Autowired
    private IRoleResourceService roleResourceDaoImpl;

    /**
     * 将Cas服务端返回JSON信息解析，并放入相应session中
     * 
     * @param paramString
     * @return
     * @throws UsernameNotFoundException
     * @author WangWB
     * @date Sep 6, 2011
     */
    @Override
    public UserDetails loadUserByUsername(String serverJson) throws UsernameNotFoundException {
        // 解析Cas服务端返回JSON，将当前用户登录名和整体json信息记录到userdetail中
        try {
            serverJson = URLDecoder.decode(serverJson, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        List<String> roleCodes = new ArrayList<String>();
        CasUser jsonUser = null;
        try {
            jsonUser = JSONUtils.jsonToObj(serverJson, new TypeReference<CasUser>() {
            });
            List<CasSystems> sysList = jsonUser.getSystemList();
            if (sysList != null) {
                for (CasSystems system : sysList) {
                    List<CasRole> roleList = system.getRoleList();
                    if (roleList != null) {
                        for (CasRole role : roleList) {
                            roleCodes.add(role.getRoleCode());
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        // 绑定权限
        Collection<GrantedAuthority> grantedAuths;
        try {
            // 将用户与其拥有权限的资源相对应,多传入List<String>类型的roleCode
            grantedAuths = obtionGrantedAuthorities(roleCodes);
            boolean enables = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            User userdetail = new User(serverJson, jsonUser.getLoginID(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
            return userdetail;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 取得用户的权限
     * 
     * @param user
     * @return
     * @author ZhangKW
     * @date 2011-5-26
     */
    @SuppressWarnings( { "deprecation" })
    private Set<GrantedAuthority> obtionGrantedAuthorities(List<String> roleCodes) throws Exception {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        // modify by RongLT 2011-11-09-----start
        // 往用户权限中添加拥有权限的资源id
        if (roleCodes != null && roleCodes.size() > 0) {
            Iterator<String> ite = roleCodes.iterator();
            RoleResourceCondition condition = new RoleResourceCondition();
            RoleResourceMenuBean roleResourceMenuBean = null;
            String[] roleCodesArr = new String[roleCodes.size()];
            int idx = 0;
            while (ite.hasNext()) {
                String queryRoleCode = (String) ite.next();
                roleCodesArr[idx++] = queryRoleCode;
            }
            condition.setRoleCodes(roleCodesArr);
            List<RoleResourceMenuBean> roleResources = roleResourceDaoImpl.getRoleResourceByRoleId(condition);
            if (roleResources != null) {
                Iterator<RoleResourceMenuBean> resources = roleResources.iterator();
                while (resources.hasNext()) {
                    roleResourceMenuBean = (RoleResourceMenuBean) resources.next();
                    if (roleResourceMenuBean.getResourceID().toString() != null && !"".equals(roleResourceMenuBean.getResourceID().toString())) {
                        authSet.add(new GrantedAuthorityImpl(roleResourceMenuBean.getResourceID().toString()));
                    }
                }
            }
            // -----end
        }
        // 登录后的所有用户增加一个已登录资源访问权限
        authSet.add(new GrantedAuthorityImpl("ROLE_USER"));
        return authSet;
    }

}
