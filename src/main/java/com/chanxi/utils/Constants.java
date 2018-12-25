package com.chanxi.utils;

public final class Constants {


    public static class Cookie {

        public static class Fields {

            //存放加密uid
            public static final String UID = "uid";
            //存放加密登陆信息
            public static final String UKEY = "ukey";
            public static final String UCCID = "uccid";
            public static final String UUID = "uuid";
            //cookie track diff time
            public static final String DT = "DT";
            //cookie track id
            public static final String TID = "TID";
            // cookie track browser property
            public static final String TBP = "TBP";
            public static final String CHANNEL = "channel";
            public static final String CHANNELTAG = "channelTag";
            public static  final String IPPath ="/data/rongyiju/ip/17monipdb.datx";

        }
    }

    public static class Session {
        public static class Fields {
            //存放加密uid
            public static final String UID = "uid";
            //存放加密登陆信息
            public static final String UKEY = "ukey";
            //存放后台admin user对象
            public static final String USER = "account";
        }
    }
    public static class project {
        public static String name  = "rongeju";
        public static String HOUSING  = "housing";
        public static String OPERATION  = "operation";
        public static String CREDIT  = "credit";
        public static String VEHICLE  = "vehicle";
    }
    public static class SysDictKey{
        public static final String SYSDICT="SYSDICT";
            public static final String KEYWORDS="keywords";
    }
    public static class SysVarKey{
        public static class Loan{
            public static final String DIRECTORY="navigationDirectory";
        }
    }
    public static class CachKey{
        public static final String INDEX="index";
        public static final String NEWSiNDEX="NEWSiNDEX";
        public static final String NEWSCOMMON="NEWSCOMMON";
        public static final String HOUSEINDEX="house_index";
        public static final String HOUSEINFO="house_info";
    }
    public static class CachTime{
        public static final int SYSVAR=60;
        public static final int COMMON=60*5;
        public static final int COMMON_AREA_LIST=7*24*3600;
    }
    public  static class RedisKey{
        private static final String REDISKEY_PRE="rongyiju-portal";
        public static final String SYS_DICT_VALUE_MAP=REDISKEY_PRE+"sysDictValueMap";
        public static class SysKey{
            public static final String SYSDICT ="sysDict";
            public static final String SYS_DICT_VALUE_MAP=REDISKEY_PRE+"sysDictValueMap";
        }
        public static class Key{


        }
    }
    public static class RedisTime{
        public static final int SYSVAR=60;
        public static final int COMMON=60;
        public static final int COMMON_AREA_LIST=7*24*3600;
    }

    public static final class ConfKey{
        public static final String YIJU_IP_LIST = "yiju_ip_list";
    }
}
