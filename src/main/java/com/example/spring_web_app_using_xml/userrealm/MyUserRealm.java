package com.example.spring_web_app_using_xml.userrealm;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.security.UserRealm;

import java.security.Principal;

public class MyUserRealm implements UserRealm {
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
        if (username.equals("chris")) {
            return () -> "NameOfChrisPrincipal";
        }
        return null;
    }

    @Override
    public boolean reauthenticate(Principal user) {
        return false;
    }

    @Override
    public boolean isUserInRole(Principal user, String role) {
        if (user.getName().equals("NameOfChrisPrincipal") && role.equals("CoolUserRole")) {
            return true;
        } else {
            return false;
        }
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
