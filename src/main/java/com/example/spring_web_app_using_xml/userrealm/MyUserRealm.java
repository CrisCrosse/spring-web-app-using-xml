package com.example.spring_web_app_using_xml.userrealm;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.security.UserRealm;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class MyUserRealm implements UserRealm {

//    Map<String, String> usernameRoleMappings = new HashMap<>();
//
//    public MyUserRealm() {
//        usernameRoleMappings.put("chris", "CoolUserRole");
//    };


    @Override
    public String getName() {
        return "";
    }

    @Override
    public Principal getPrincipal(String username) {
        return null;
    }

    @Override
    public Principal authenticate(String username, Object credentials, Request request) {
//        if (username.equals("chris")) {
//            return () -> "NameOfChrisPrincipal";
//        }
        return null;
    }

    @Override
    public boolean reauthenticate(Principal user) {
        return false;
    }

    @Override
    public boolean isUserInRole(Principal user, String role) {
        return false;
    }

    @Override
    public void disassociate(Principal user) {

    }

    @Override
    public Principal pushRole(Principal user, String role) {
        return null;
    }

    @Override
    public Principal popRole(Principal user) {
        return null;
    }

    @Override
    public void logout(Principal user) {
    }
}
