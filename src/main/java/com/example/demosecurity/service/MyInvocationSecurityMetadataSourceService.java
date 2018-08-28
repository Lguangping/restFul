package com.example.demosecurity.service;


import com.example.demosecurity.domain.Permission;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Service
public class MyInvocationSecurityMetadataSourceService  implements
    FilterInvocationSecurityMetadataSource {

    private HashMap<String, Collection<ConfigAttribute>> map =null;

    /**
     * 加载权限表中所有权限
     */
    public void loadResourceDefine(){
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        // todo 修改permissions内容
        List<Permission> permissions =new ArrayList<>();
        permissions.add(new Permission(1, "ROLE_HOME", "可以访问home权限", "/home", 1,"GET"));
        permissions.add(new Permission(2, "ROLE_ADMIN", "访问amin权限", "/admin", 2,"POST"));
        permissions.add(new Permission(3, "ROLE_USER_GET", "user下所有get请求", "/user/**", 1,"GET"));
        permissions.add(new Permission(4, "ROLE_USER_POST", "user下所有post请求", "/user/**", 2,"POST"));
        permissions.add(new Permission(5, "ROLE_USER_PUT", "user下所有put请求", "/user/**", 1,"PUT"));
        permissions.add(new Permission(6, "ROLE_USER_ALL", "user下所有all请求", "/user/**", 2,"ALL"));
        for(Permission permission : permissions) {
            array = new ArrayList<>();
            cfg = new SecurityConfig(permission.getName());
            //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(cfg);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(permission.getUrl(), array);
        }

    }

    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map ==null) loadResourceDefine();
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    //因为我不想每一次来了请求，都先要匹配一下权限表中的信息是不是包含此url，
    // 我准备直接拦截，不管请求的url 是什么都直接拦截，然后在MyAccessDecisionManager的decide 方法中做拦截还是放行的决策。
    //所以此方法的返回值不能返回 null 此处我就随便返回一下。
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        Collection<ConfigAttribute> co=new ArrayList<>();
//        co.add(new SecurityConfig("null"));
//        return co;
//    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
