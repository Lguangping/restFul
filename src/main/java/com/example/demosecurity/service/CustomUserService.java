package com.example.demosecurity.service;


import com.example.demosecurity.domain.Permission;
import com.example.demosecurity.domain.SysRole;
import com.example.demosecurity.domain.SysUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口


    @Override
    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户

        List<SysRole> sysRoles=new ArrayList<>();
        if("admin".equals(username)){
            sysRoles.add(new SysRole(1,"ROLE_ADMIN"));
            sysRoles.add(new SysRole(2,"ROLE_NEWROLE"));
        }else{
            sysRoles.add(new SysRole(3,"ROLE_USER"));
        }
        SysUser user;
        if("admin".equals(username)){
            user = new SysUser(1, "admin用户", "aa123", sysRoles);
        }else{
            user = new SysUser(1, "其他用户", "123456", sysRoles);
        }

//        if(user == null){
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//        for(SysRole role:user.getRoles()) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
////            System.out.println(role.getName());
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),
//                                                                      user.getPassword(), authorities);

        if (user != null) {
            List<Permission> permissions =new ArrayList<>();
            if("admin".equals(username)){
                permissions.add(new Permission(1, "ROLE_HOME", "可以访问home权限", "/home", 1,"GET"));
                permissions.add(new Permission(2, "ROLE_ADMIN", "可以访问amin权限", "/admin", 2,"GET"));
                permissions.add(new Permission(6, "ROLE_USER_ALL", "user下所有all请求", "/user/**", 2,"ALL"));
            }else{
                permissions.add(new Permission(1, "ROLE_HOME", "可以访问home权限", "/home", 1,"GET"));
                permissions.add(new Permission(3, "ROLE_USER_GET", "user下所有get请求", "/user/**", 1,"GET"));
            }
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {

                    GrantedAuthority grantedAuthority = new MyGrantedAuthority(permission.getUrl(), permission.getMethod());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }



    }
}
