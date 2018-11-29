package top.joylife.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.joylife.tracker.common.ReData;
import top.joylife.tracker.common.bean.dto.NetworkTokenDto;
import top.joylife.tracker.common.util.ReUtil;
import top.joylife.tracker.service.NetworkTokenService;

import java.util.List;

@RestController
@RequestMapping("networkToken")
public class NetworkTokenController {

    @Autowired
    private NetworkTokenService networkTokenService;

    /**
     * 列出所有的networkToken
     * @return
     */
    @GetMapping("/list")
    public ReData<List<NetworkTokenDto>> listAll(){
        List<NetworkTokenDto> networkTokenDtos = networkTokenService.listAll();
        return ReUtil.success(networkTokenDtos);
    }
}
