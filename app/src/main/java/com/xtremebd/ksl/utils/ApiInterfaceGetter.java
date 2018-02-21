package com.xtremebd.ksl.utils;

import com.xtremebd.ksl.clients.ApiClient;
import com.xtremebd.ksl.interfaces.DynamicApiInterface;

/**
 * Created by Mashnoor on 8/15/17.
 */

public class ApiInterfaceGetter {

    private ApiInterfaceGetter(){}

    private static DynamicApiInterface dynamicStaticApiInterface = null;

   
    public static DynamicApiInterface getDynamicInterface()
    {
        if(dynamicStaticApiInterface == null)
        {
            dynamicStaticApiInterface =  ApiClient.getDynamicClient().create(DynamicApiInterface.class);

        }
        return dynamicStaticApiInterface;
    }

}
