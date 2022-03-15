package com.messenger.constants.controllers;

public class Endpoints
{
    //  Conversation controller
    public static final String CONVERSATIONS = "conversations";
    public static final String NEW = "new";

    //  Messages controller
    public static final String MESSAGES = "conversations/{uuid}/messages";

    //  Profile controller
    public static final String PROFILE = "/profile/{uuid}";

    //  Admin controller
    public static final String ADMIN = "admin";
    public static final String ADMIN_GT_USER_ID = "/admin/gt/{userId}";

    //  Registration controller
    public static final String REGISTRATION = "/registration";
}
