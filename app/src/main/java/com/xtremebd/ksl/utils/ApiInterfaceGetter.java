package com.xtremebd.ksl.utils;

import com.xtremebd.ksl.clients.ApiClient;
import com.xtremebd.ksl.interfaces.ApiInterface;

/**
 * Created by Mashnoor on 8/15/17.
 */

public class ApiInterfaceGetter {

    private ApiInterfaceGetter(){}
    private static ApiInterface apiInterface = null;

    public static ApiInterface getInterface()
    {
        if(apiInterface == null)
        {
            apiInterface =  ApiClient.getClient().create(ApiInterface.class);

        }
        return apiInterface;
    }

}
