package com.messenger.constants.controllers;

public class Endpoints
{
    // Conversation controller
    public static final String CONVERSATIONS = "/conversations";

    public static final String CONVERSATION_ID = "/{id}";
    public static final String LEAVE_CONVERSATION = "/{id}/leave";
    public static final String NEW = "/new";

    //  Messages controller
    public static final String MESSAGES = "/conversations/{id}/messages";

    public static final String ADD_NEW_MEMBER = "/add-new-member";

    //  Profile controller
    public static final String PROFILE = "/profile/{id}";

    //  Admin controller
    public static final String ADMIN = "/admin";

    //  Registration controller
    public static final String REGISTRATION = "/registration";

    //  Export controller
    public static final String API_V1 = "/api/v1/";
    public static final String CONVERSATION_BY_ID_API = "/conversations/{conversation_id}";
    public static final String USERS_API = "/users";
    public static final String USER_BY_ID_API = "/users/{user_id}";
}
