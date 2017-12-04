package com.xtremebd.ksl.interfaces;


import com.xtremebd.ksl.models.Account;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.models.Index;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.models.MarketSummary;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.models.News;
import com.xtremebd.ksl.models.Notification;
import com.xtremebd.ksl.models.PortfolioStatement;
import com.xtremebd.ksl.models.Requisition;
import com.xtremebd.ksl.models.Resp;

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
    @POST("masterlogin")
    Call<String> masterLogin(@Body MasterAccount masterAccount);
    @POST("itsaccounts")
    Call<List<ITSAccount>> getItsAccounts(@Body MasterAccount masterAccount);
    @POST("{masterid}/additsaccountmobile")
    Call<String> addItsAccount(@Path("masterid") String masterId, @Body ITSAccount itsAccount);
    @GET("getnotifications/{masterid}")
    Call<List<Notification>> getNotifications(@Path("masterid") String masterId);
    @POST("settoken")
    Call<String> setToken(@Body MasterAccount masterAccount);
    @POST("clientids")
    Call<List<String>> getClientIds(@Body MasterAccount masterAccount);
    @POST("getportfoliostatement")
    Call<Resp> getPortfolioStatement(@Body PortfolioStatement ps);



}
