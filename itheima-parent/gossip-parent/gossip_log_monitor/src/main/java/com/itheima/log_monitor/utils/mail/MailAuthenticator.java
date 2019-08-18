package com.itheima.log_monitor.utils.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.io.Serializable;

public class MailAuthenticator extends Authenticator implements Serializable {
    private static final long serialVersionUID = -6171559335713133909L;
    String userName;
    String userPassword;

    public MailAuthenticator() {
        super();
    }

    public MailAuthenticator(String user, String pwd) {
        super();
        userName = user;
        userPassword = pwd;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, userPassword);
    }

}

