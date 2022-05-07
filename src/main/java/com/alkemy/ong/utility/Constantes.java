package com.alkemy.ong.utility;



public class Constantes {

     public static final String REQUEST_ID = "/{id}";
     public static final String REQUEST_NAME = "/{name}";
     public static final String ORGANIZATION_MAP_REQUEST = "/organization/public";
     public static final String AUTH_URL = "/auth/";
     public static final String LOGIN_URL = "/login";
     public static final String REGISTER_URL = "/register";
     public static final String CATEGORY_URL="/category";
     public static final String MEMBER_RUL="/member";
     public static final String USER_GET="/users";
     public static final String USER_PATCH="/users/{id}";
     public static final String USER_AUTH_ME= "auth/me";
     public static final String USER_REGISTER= "/auth/register";

     //AmazonS3
     public static final String AWS_STORAGE_REQUEST = "storage/";
     public static final String AWS_DELETE_FILE = "/uploadFile";
     public static final String AWS_UPLOAD_FILE = "/uploadFile";
     public static final String AWS_EXCEPTION_NOT_FOUND = "Not file to upload found";
     public static final String AWS_EXCEPTION_ERR_UPLOAD = "Error while uploading file. It could not be saved";
     public static final String NEWS_URL="/news";
     public static final String ACTIVITY_URL="/activity";
}
