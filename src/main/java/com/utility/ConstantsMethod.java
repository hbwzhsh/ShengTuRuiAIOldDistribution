package com.utility;

import com.bean.Device;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.Set;

/**
 * Servlet implementation class InitializeData
 */
public class ConstantsMethod {


    public static String getProcessBarCmd(String persentage) {
        if (StringUtils.isBlank(persentage)) {
            return "0000";
        }
        return ToHexUtil.pad(ToHexUtil.toHex(Integer.parseInt(persentage)), 4, true);
    }


    public static String devicePKey(String mac,String ep){
        return "p"+mac+":"+ep;
    }

    public static String deviceNameKey(String mac,String ep){
        return "name"+mac+":"+ep;
    }


}
