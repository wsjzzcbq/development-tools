package com.wsjzzcbq.web.service.impl;

import com.wsjzzcbq.constant.AppConsts;
import com.wsjzzcbq.constant.NetworkProtocolConsts;
import com.wsjzzcbq.util.IPUtils;
import com.wsjzzcbq.util.SysUtils;
import com.wsjzzcbq.web.bean.R;
import com.wsjzzcbq.web.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * MsgServiceImpl
 *
 * @author wsjz
 * @date 2022/06/06
 */
@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private Environment env;

    @Override
    public R<?> getWebSocketInfo() {
        String ip = IPUtils.getIpv4();
        String port = env.getProperty("server.port");

        //此处uuid可以扩展做用户websocket连接前的身份效验
        String userId = SysUtils.uuid();
        String url = NetworkProtocolConsts.WEBSOCKET + ip + ":" + port + AppConsts.SERVER_END_POINT_URL + userId;
        return R.ok(url);
    }
}
