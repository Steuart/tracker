package top.joylife.tracker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.joylife.tracker.common.bean.dto.TokensDto;
import top.joylife.tracker.common.bean.param.TokensParam;
import top.joylife.tracker.dao.entity.Tokens;
import top.joylife.tracker.dao.impl.TokensDao;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据idref列表获取idref和token的映射
     * @param idRefs
     * @param type
     * @return
     */
    public Map<Integer,List<TokensDto>> mapByIdRefsAndType(List<Integer> idRefs, Integer type){
        List<Tokens> tokens = tokensDao.listByIdRefsAndType(idRefs,type);
        Map<Integer,List<TokensDto>> result = new HashMap<>();
        if(CollectionUtils.isEmpty(tokens)){
            return result;
        }
        tokens.forEach(token -> {
            Integer idRef = token.getIdRef();
            List<TokensDto> tokensDtos = result.get(idRef);
            if(tokensDtos == null){
                tokensDtos = new ArrayList<>();
                result.put(idRef,tokensDtos);
            }
            TokensDto tokensDto = new TokensDto();
            BeanUtils.copyProperties(tokens, tokensDto);
            tokensDtos.add(tokensDto);
        });
        return result;
    }

    /**
     * 批量添加token
     * @param params
     * @param refId
     * @param type
     */
    public void batchAddTokens(List<TokensParam> params,Integer refId, Tokens.TypeEnum type){
        List<Tokens> tokens = generateTokenFromParam(refId, params,type);
        tokensDao.batchAddTokens(tokens);
    }

    /**
     * 批量添加token
     * @param params
     * @param refId
     * @param type
     */
    public void batchUpdateTokens(List<TokensParam> params,Integer refId, Tokens.TypeEnum type){
        tokensDao.deleteByIdRefAndType(refId,type.getCode());
        List<Tokens> tokens = generateTokenFromParam(refId, params,type);
        tokensDao.batchAddTokens(tokens);
    }


    /**
     * 生成Tokens
     * @param param
     * @return
     */
    private List<Tokens> generateTokenFromParam(Integer refId, List<TokensParam> param, Tokens.TypeEnum type){
        List<Tokens> tokens = new ArrayList<>();
        param.forEach(tokenParam ->{
            Tokens token = new Tokens();
            BeanUtils.copyProperties(tokenParam, token);
            token.setIdRef(refId);
            token.setType(type.getCode());
            tokens.add(token);
        });
        return tokens;
    }
}
