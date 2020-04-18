package com.baby.babyproject.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
*@ClassName FormatJsonUtil
*@Description json格式化
*@Author lilinsong
*@Date 2020/1/2 15:57
*@Version 1.0
*/
public class FormatJsonUtil {

    /**
     * @MethodName jsonFormat
     * @Author lilinsong
     * @Description  json字符串格式化
     * @Param [jsonString]
     * @return java.lang.String
     * @Date 2020/1/2 16:02 
     **/
    public static String jsonFormat(String jsonString) {
        JSONObject object= JSONObject.parseObject(jsonString);
        jsonString = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        return jsonString;
    }


    /**
     * @MethodName formatJson
     * @Author lilinsong
     * @Description  json格式化
     * @Param [jsonStr]
     * @return java.lang.String
     * @Date 2020/1/2 15:59 
     **/
    public static String formatJson(String jsonStr)
    {
        if (null == jsonStr || "".equals(jsonStr))
        {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++)
        {
            last = current;
            current = jsonStr.charAt(i);
            switch (current)
            {
                case '"':
                    if (last != '\\')
                    {
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;

                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks)
                    {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;

                case '}':
                case ']':
                    if (!isInQuotationMarks)
                    {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;

                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks)
                    {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;

                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     *
     * @param sb
     * @param indent
     * @author lizhgb
     * @Date 2015-10-14 上午10:38:04
     */
    private static void addIndentBlank(StringBuilder sb, int indent)
    {
        for (int i = 0; i < indent; i++)
        {
            sb.append('\t');
        }
    }
}