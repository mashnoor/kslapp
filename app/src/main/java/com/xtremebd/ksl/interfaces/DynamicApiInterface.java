package com.xtremebd.ksl.interfaces;


import com.xtremebd.ksl.models.Account;
import com.xtremebd.ksl.models.Index;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.models.MarketSummary;
import com.xtremebd.ksl.models.News;
import com.xtremebd.ksl.models.Requisition;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;


/**
 * Created by Mashnoor on 8/14/17.
 */

public interface DynamicApiInterface {


    @POST("requestaccount")
    Call<String> submitAccountRequest(@Body Account account);
    @POST("requestrequisition")
    Call<String> submitFundRequisitionRequest(@Body Requisition requisition);


}
