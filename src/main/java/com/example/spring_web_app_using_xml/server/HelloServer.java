package com.example.spring_web_app_using_xml.server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;

public class HelloServer extends Server {
    public Context context;

    public HelloServer(int port) {
        super(port);
        context = new Context(this, "/api/v1", true, true);
    }

    public Context getContext() {
        return context;
    }
}
