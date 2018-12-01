package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.param.TokensParam;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.Tokens;
import top.joylife.tracker.dao.mapper.TokensMapper;

import java.util.List;

@Repository
public class TokensDao extends BaseDao<Tokens>{

    @Autowired
    private TokensMapper tokensMapper;

    @Override
    public MyMapper<Tokens> getMapper() {
        return tokensMapper;
    }

    /**
     * 构建分页查询条件
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Example buildPageQueryExample(BasePageQuery pageQuery) {
        return null;
    }


    /**
     * 根据idref和type查询列表
     * @param id
     * @param type
     * @return
     */
    public List<Tokens> listByIdRefAndType(Integer id, Integer type){
        Example example = new Example(Tokens.class);
        example.createCriteria()
                .andEqualTo("idRef",id)
                .andEqualTo("type",type);
        return tokensMapper.selectByExample(example);
    }


    /**
     * 保存campaignToken
     * @param tokens
     */
    public void batchAddTokens(List<Tokens> tokens){
        tokens.forEach(campaignToken -> {
            tokensMapper.insertSelective(campaignToken);
        });
    }

    /**
     * 根据idRef和类型删除
     * @param idRef
     * @param type
     */
    public void deleteByIdRefAndType(Integer idRef, Integer type) {
        Example example = new Example(Tokens.class);
        example.createCriteria()
                .andEqualTo("idRef", idRef)
                .andEqualTo("type", type);
        tokensMapper.deleteByExample(example);
    }

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    public List<Tokens> selectByName(String name){
        Example example = new Example(Tokens.class);
        example.createCriteria()
                .andEqualTo("name",name);
        return tokensMapper.selectByExample(example);
    }
}
