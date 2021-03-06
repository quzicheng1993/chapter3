package org.smart4j.chapter1.util;

public class CastUtil {

    /**
     * 转换String类型
     * @param obj
     * @return
     */
    public static String castString(Object obj){
        return CastUtil.castString(obj,"");
    }

    /**
     * 转换String类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj,String defaultValue){
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为double型
     * @param obj
     * @return
     */
    public static double castDouble(Object obj){
        return CastUtil.castDouble(obj,0);
    }

    /**
     * 转换double类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj,double defaultValue){
        double doubleValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    doubleValue = Double.parseDouble(strValue);
                }catch(NumberFormatException e){
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转换为long类型
     * @param obj
     * @return
     */
    public static long castLong(Object obj){
            return CastUtil.castLong(obj,0);
    }

    /**
     * 转换为long类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj,long defaultValue){
        long longValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
                if(StringUtil.isNotEmpty(strValue)){
                    try{
                        longValue = Long.parseLong(strValue);
                    }catch (NumberFormatException e){
                        longValue = defaultValue;
                    }
                }

            }
            return longValue;
    }

    /**
     * 转换为int
     * @param obj
     * @return
     */
    public static int castInt(Object obj){
        return CastUtil.castInt(obj,0);
    }

    /**
     * 转换为int型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj,int defaultValue){
        int intValue= defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                  intValue = Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                   intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转换为boolean类型
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj){
        return CastUtil.castBoolean(obj,false);
    }

    /**
     * 转换为boolean类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean booleanValue = defaultValue;
        if(obj != null){
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }
}
