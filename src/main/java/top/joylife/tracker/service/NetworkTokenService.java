package top.joylife.tracker.service;

import org.springframework.stereotype.Service;
import top.joylife.tracker.common.bean.dto.NetworkTokenDto;
import top.joylife.tracker.common.enums.CallbackParamEnum;

import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkTokenService {

    /**
     * 查询所有的networkToken
     * @return
     */
    public List<NetworkTokenDto> listAll(){
        List<NetworkTokenDto> tokenDtos = new ArrayList<>();
        for(CallbackParamEnum enums: CallbackParamEnum.values()){
            NetworkTokenDto networkToken = new NetworkTokenDto();
            networkToken.setCode(enums.getCode());
            networkToken.setName(enums.getName());
            tokenDtos.add(networkToken);
        }
        return tokenDtos;
    }
}
