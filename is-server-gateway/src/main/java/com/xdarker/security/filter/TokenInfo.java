package com.xdarker.security.filter;

import java.util.Date;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/4/17 23:11
 */
public class TokenInfo {

    private boolean active;

    private String client_id;

    private String[] scpoe;

    private String user_name;

    private String[] aud;

    //过期时间
    private Date exp;

    private String[] authorities;


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String[] getScpoe() {
        return scpoe;
    }

    public void setScpoe(String[] scpoe) {
        this.scpoe = scpoe;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String[] getAud() {
        return aud;
    }

    public void setAud(String[] aud) {
        this.aud = aud;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }
}
