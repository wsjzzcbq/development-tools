package com.wsjzzcbq.util;

import com.wsjzzcbq.constant.AppConsts;
import lombok.extern.slf4j.Slf4j;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * IPUtils
 *
 * @author wsjz
 * @date 2022/04/06
 */
@Slf4j
public class IPUtils {

    public static String getIpv4(){
        String ipv4 = "";
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()){
                NetworkInterface networkInterface = enumeration.nextElement();
                Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();
                while (addressEnumeration.hasMoreElements()){
                    InetAddress inetAddress1 = addressEnumeration.nextElement();
                    if (inetAddress1 instanceof Inet4Address) {
                        String ipv4s = inetAddress1.getHostAddress();

                        String ipFormat = YmlUtils.getYmlConfiguration().getIpFormat();
                        if (ipFormat == null) {
                            ipFormat = AppConsts.DEFAULT_IP_FORMAT;
                        }
                        if (ipv4s.indexOf(ipFormat) > -1) {
                            ipv4 = ipv4s;
                        }

                    }

                }
            }
            return ipv4;
        }catch (Exception e){
            return ipv4;
        }

    }
}
