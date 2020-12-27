package com.shashank.platform.classroomappui.api;

import com.shashank.platform.classroomappui.models.Exam;
import com.shashank.platform.classroomappui.models.NevsUser;
import com.shashank.platform.classroomappui.models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    public String authorization = "Authorization: Basic YWRtaW46MTIzNA==";
    public String contentType = "Content-Type: application/x-www-form-urlencoded";

    @FormUrlEncoded
    @POST("login")
    @Headers({
            contentType,
            authorization
    })
    public  Call<NevsUser> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("getexams")
    @Headers({
            contentType,
            authorization
    })
    public  Call<List<Exam>> getExams(@Field("token") String token);

    @FormUrlEncoded
    @POST("getnews")
    @Headers({
            contentType,
            authorization
    })
    public  Call<List<News>> getNews(@Field("token") String token);
}
