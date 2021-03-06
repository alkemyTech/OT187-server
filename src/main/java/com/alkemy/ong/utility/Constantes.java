package com.alkemy.ong.utility;



public class Constantes {

     public static final String REQUEST_ID = "/{id}";
     public static final String REQUEST_NAME = "/{name}";
     public static final String ORGANIZATION_MAP_REQUEST = "/organization/public";
     public static final String AUTH_URL = "/auth/";
     public static final String LOGIN_URL = "/login";
     public static final String REGISTER_URL = "/register";
     public static final String CATEGORY_URL="/category";
     public static final String MEMBER_URL="/member";
     public static final String USER_GET="/users";
     public static final String USER_PATCH="/users/{id}";
     public static final String COMMENT_URL= "/comments";
     public static final String USER_AUTH_ME= "/me";
     public static final String USER_REGISTER= "/auth/register";
     public static final String USER_LOGIN= "/auth/login";
     public static final String AWS_STORAGE_REQUEST = "/storage";
     public static final String AWS_DELETE_FILE = "/deleteFile";
     public static final String AWS_UPLOAD_FILE = "/uploadFile";
     public static final String AWS_EXCEPTION_NOT_FOUND = "Not file to upload found";
     public static final String AWS_EXCEPTION_ERR_UPLOAD = "Error while uploading file. It could not be saved";
     public static final String NEWS_URL="/news";
     public static final String ACTIVITY_URL="/activity";
     public static final String CONTACT_URL="/contacts";
     public static final Integer PAGE_SIZE = 10;
     public static final String PAGE_URL = "localhost:8080/news?page=";
     public static final String TESTIMONIAL_URL = "/testimonials";
     public static final String SLIDE_URL="/slides";
     public static final String SLIDE_ID="/slides/{id}";//Organization
     public static final String ORGANIZATION_ALL= "/organization/all";
     public static final String ORGANIZATION_ID="/organization/{id}";
     public static final String[] SWAGGER_URL = {"/api/docs/**","swagger-ui.html","/swagger-ui/**", "/v3/api-docs/**"};

}
