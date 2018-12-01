package top.joylife.tracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.bean.dto.TokensDto;
import top.joylife.tracker.common.bean.param.TokensParam;
import top.joylife.tracker.dao.entity.Tokens;
import top.joylife.tracker.dao.impl.TokensDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class TokensService {

    @Autowired
    private TokensDao tokensDao;

    /**
     * 保存tokens
     * @param tokensParam
     * @return
     */
    public Integer saveTokens(TokensParam tokensParam){
        Tokens tokens = new Tokens();
        BeanUtils.copyProperties(tokensParam, tokens);
        return tokensDao.insert(tokens);
    }

    /**
     * 更新tokens
     * @param id
     * @param param
     * @return
     */
    public Integer updateTokens(Integer id,TokensParam param){
        Tokens tokens = new Tokens();
        BeanUtils.copyProperties(param,tokens);
        tokens.setId(id);
        tokensDao.updateById(tokens);
        return id;
    }

    /**
     * 删除tokens
     * @param id
     * @return
     */
    public Integer deleteTokens(Integer id){
        tokensDao.deleteById(id);
        return id;
    }

    /**
     * 根据类型和idref查询
     * @param idRef
     * @param type
     * @return
     */
    public List<TokensDto> listByIdRefAndType(Integer idRef,Integer type){
        List<Tokens> tokensList = tokensDao.listByIdRefAndType(idRef,type);
        if(CollectionUtils.isEmpty(tokensList)){
            return null;
        }
        List<TokensDto> tokensDtos = new ArrayList<>();
        tokensList.forEach(tokens -> {
            TokensDto tokensDto = new TokensDto();
            BeanUtils.copyProperties(tokens, tokensDto);
            tokensDtos.add(tokensDto);
        });
        return tokensDtos;
    }
}
