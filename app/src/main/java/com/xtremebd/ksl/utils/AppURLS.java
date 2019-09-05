package com.xtremebd.ksl.utils;

/**
 * Created by Mashnoor on 7/4/17.
 */

public class AppURLS {
    private final static String BASE_URL = "http://103.115.24.14";
    private final static String GUNICORN_URL = "http://103.115.24.14";
    public static final String GET_LTP_URL = GUNICORN_URL + ":5003/getltp/";
    public static final String TRADE_URL = GUNICORN_URL + ":5003/trade";
    public static final String IPO_URL = BASE_URL + "/kslbackend/ipo.txt";
    public static final String GET_PORTFOLIO_STATEMENT = GUNICORN_URL + ":5003/getportfoliostatement";
    public static final String GET_ORDER_STATUS = GUNICORN_URL + ":5003/getorderstatus";
    public static final String GET_FINANCIAL_LEDGER = GUNICORN_URL + ":5003/getfinancialledgrs";
    public static final String DELETE_ITS_ACCOUNT = GUNICORN_URL + ":5003/deleteitsaccount";
    public static final String GET_KSL_NEWS = BASE_URL + "/kslbackend/ksl_news.txt";
    public static final String GET_TOP_GAINERS = GUNICORN_URL + ":5003/gettop/gainers";
    public static final String GET_TOP_LOSERS = GUNICORN_URL + ":5003/gettop/losers";
    public static final String GET_DAY_END_DATA = GUNICORN_URL + ":5003/getdayenddata";
    public static final String GET_ITEM_DETAIL = GUNICORN_URL + ":5003/getitemdetail/";
    public static final String GET_MARKET_SUMMARY = BASE_URL + ":5003/csehomedata/market_summary";
    public static final String GET_INDEX = BASE_URL + ":5003/csehomedata/all_indexes";
    public static final String GET_MARKET_DEPTH = GUNICORN_URL + ":5003/getmarketdepth/";
    public static final String GET_ALL_ITEMS_UPDATES = BASE_URL + ":5003/getlatestitems";
    public static final String GET_CSE_NEWS = GUNICORN_URL + ":5003/getlatestnews";
    public static final String GET_ITEM_NEWS = GUNICORN_URL + ":5003/getitemnews/";
    public static final String PASSWORD_RECOVER = GUNICORN_URL + ":5003/recoverpassword";
    public static final String MASTER_LOGIN = GUNICORN_URL + ":5003/masterlogin";
    public static final String SET_TOKEN = GUNICORN_URL + ":5003/settoken";
    public static final String GET_ITS_ACCOUNTS = GUNICORN_URL + ":5003/itsaccounts";
    public static final String ADD_ITS_ACCOUNT = GUNICORN_URL + ":5003/additsaccountmobile";
    public static final String GET_CLIENT_ID = GUNICORN_URL + ":5003/clientids";
    public static final String REQUEST_FUND_REQUISITION = GUNICORN_URL + ":5003/requestrequisition";
    public static final String GET_NOTIFICATIONS = GUNICORN_URL + ":5003/getnotifications";
    public static final String REQUEST_ACCOUNT = GUNICORN_URL + ":5003/requestaccount";
    public static final String GET_MARKET_MOVERS_BY_TRADE = BASE_URL + ":5003/getmarketmovers/bytrade";
    public static final String GET_MARKET_MOVERS_BY_VOLUME = BASE_URL + ":5003/getmarketmovers/byvolume";
    public static final String GET_MARKET_MOVERS_BY_VALUE = BASE_URL + ":5003/getmarketmovers/byvalue";
    public static final String GET_DSE_MARKET_SUMMARY = BASE_URL + ":5003/dsehomedata";
    public static final String UPDATE_ITS_PASSWORD = GUNICORN_URL + ":5003/updateitspassword";


}
