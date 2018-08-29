package com.example.demosecurity.service;

import com.example.demosecurity.domain.SysRole;
import com.example.demosecurity.domain.SysUser;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Service
public class SampleCrudService {

    public SysUser saveOneUser(SysUser user) {
        return user;
    }

    public SysUser findOneUserById(String id) {
        List<SysRole> sysRoles=new ArrayList<>();
        sysRoles.add(new SysRole(1,"ROLE_ADMIN"));
        sysRoles.add(new SysRole(2,"ROLE_NEWROLE"));
        return new SysUser(1, "admin用户", "aa123", sysRoles);
    }

    public void deleteOneUserById(String id) {}

    public SysUser updateOneUser(SysUser user,String userId) {
        return user;
    }

    public SysUser patchOneUser(String username,String userId) {
        List<SysRole> sysRoles=new ArrayList<>();
        sysRoles.add(new SysRole(1,"ROLE_ADMIN"));
        sysRoles.add(new SysRole(2,"ROLE_NEWROLE"));
        return new SysUser(1, username, "aa123", sysRoles);
    }

}
