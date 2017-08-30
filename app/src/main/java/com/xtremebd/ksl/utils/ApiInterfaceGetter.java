package com.xtremebd.ksl.utils;

import com.xtremebd.ksl.clients.ApiClient;
import com.xtremebd.ksl.interfaces.DynamicApiInterface;
import com.xtremebd.ksl.interfaces.StaticApiInterface;

/**
 * Created by Mashnoor on 8/15/17.
 */

public class ApiInterfaceGetter {

    private ApiInterfaceGetter(){}
    private static StaticApiInterface staticStaticApiInterface = null;
    private static DynamicApiInterface dynamicStaticApiInterface = null;

    public static StaticApiInterface getStaticInterface()
    {
        if(staticStaticApiInterface == null)
        {
            staticStaticApiInterface =  ApiClient.getStaticClient().create(StaticApiInterface.class);

        }
        return staticStaticApiInterface;
    }
    public static DynamicApiInterface getDynamicInterface()
    {
        if(dynamicStaticApiInterface == null)
        {
            dynamicStaticApiInterface =  ApiClient.getDynamicClient().create(DynamicApiInterface.class);

        }
        return dynamicStaticApiInterface;
    }

}
