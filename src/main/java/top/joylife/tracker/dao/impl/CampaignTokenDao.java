package top.joylife.tracker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import top.joylife.tracker.common.bean.query.BasePageQuery;
import top.joylife.tracker.dao.MyMapper;
import top.joylife.tracker.dao.entity.CampaignToken;
import top.joylife.tracker.dao.mapper.CampaignTokenMapper;

import java.util.List;

@Repository
public class CampaignTokenDao extends BaseDao<CampaignToken> {

    @Autowired
    private CampaignTokenMapper campaignTokenMapper;

    @Override
    public MyMapper<CampaignToken> getMapper() {
        return campaignTokenMapper;
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
     * 保存campaignToken
     * @param campaignTokens
     */
    public void batchAddCampaignToken(List<CampaignToken> campaignTokens){
        campaignTokens.forEach(campaignToken -> {
            campaignTokenMapper.insertSelective(campaignToken);
        });
    }

    /**
     * 根据campaignId删除
     * @param campaignId
     */
    public void deleteByCampaignId(Integer campaignId){
        Example example = new Example(CampaignToken.class);
        example.createCriteria()
                .andEqualTo("campaignId", campaignId);
        campaignTokenMapper.deleteByExample(example);
    }

    /**
     * 根据campaignId查询
     * @param campaignId
     * @return
     */
    public List<CampaignToken> listByCampaignId(Integer campaignId){
        Example example = new Example(CampaignToken.class);
        example.createCriteria()
                .andEqualTo("campaignId", campaignId);
        return campaignTokenMapper.selectByExample(example);
    }

    /**
     * 根据名字查询campaignToken
     * @param name
     * @return
     */
    public List<CampaignToken> selectByName(String name){
        Example example = new Example(CampaignToken.class);
        example.createCriteria()
                .andEqualTo("name", name);
        return campaignTokenMapper.selectByExample(example);
    }
}
