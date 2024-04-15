package com.hackorange.pixify.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import com.hackorange.pixify.dto.*;
import com.hackorange.pixify.mapper.*;
import com.hackorange.pixify.model.Article;
import com.hackorange.pixify.model.WebsiteConfig;
import com.hackorange.pixify.service.IBlogInfoService;
import com.hackorange.pixify.service.IPageService;
import com.hackorange.pixify.service.IUniqueViewService;
import com.hackorange.pixify.service.PageService;
import com.hackorange.pixify.util.BeanCopyUtils;
import com.hackorange.pixify.util.IpUtils;
import com.hackorange.pixify.vo.BlogInfoVO;
import com.hackorange.pixify.vo.PageVO;
import com.hackorange.pixify.vo.WebsiteConfigVO;
import com.twelvet.framework.RedisUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.util.*;
import java.util.stream.Collectors;

import static com.hackorange.pixify.constant.CommonConst.*;
import static com.hackorange.pixify.constant.RedisPrefixConst.*;
import static com.hackorange.pixify.enums.TalkStatusEnum.PUBLIC;


/**
 * 博客信息服务
 *
 * @author xiaojie
 * @date 2021/08/10
 * @since 2020-05-18
 */
@Service
public class BlogInfoServiceImpl implements IBlogInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private IUniqueViewService uniqueViewService;
    @Autowired
    private WebsiteConfigMapper websiteConfigMapper;
    @Resource
    private HttpServletRequest request;
    @Autowired
    private IPageService pageService;

    @Override
    public BlogHomeInfoDTO getBlogHomeInfo() {
        // 查询文章数量
        Integer articleCount = Math.toIntExact(articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                .eq(Article::getStatus, PUBLIC.getStatus())
                .eq(Article::getIsDelete, FALSE)));
        // 查询分类数量
        Integer categoryCount = Math.toIntExact(categoryMapper.selectCount(null));
        // 查询标签数量
        Integer tagCount = Math.toIntExact(tagMapper.selectCount(null));
        // 查询访问量
        Object count = RedisUtils.getCacheObject(BLOG_VIEWS_COUNT);
        String viewsCount = Optional.ofNullable(count).orElse(0).toString();
        // 查询网站配置
        WebsiteConfigVO websiteConfig = this.getWebsiteConfig();
        // 查询页面图片
        List<PageVO> pageVOList = pageService.listPages();
        // 封装数据
        return BlogHomeInfoDTO.builder()
                .articleCount(articleCount)
                .categoryCount(categoryCount)
                .tagCount(tagCount)
                .viewsCount(viewsCount)
                .websiteConfig(websiteConfig)
                .pageList(pageVOList)
                .build();
    }

    @Override
    public BlogBackInfoDTO getBlogBackInfo() {
//        // 查询访问量
//        Object count = RedisUtils.getCacheObject(BLOG_VIEWS_COUNT);
//        Integer viewsCount = Integer.parseInt(Optional.ofNullable(count).orElse(0).toString());
//        // 查询留言量
//        Integer messageCount = Math.toIntExact(messageMapper.selectCount(null));
//        // 查询用户量
//        Integer userCount = Math.toIntExact(userInfoMapper.selectCount(null));
//        // 查询文章量
//        Integer articleCount = Math.toIntExact(articleMapper.selectCount(new LambdaQueryWrapper<Article>()
//                .eq(Article::getIsDelete, FALSE)));
//        // 查询一周用户量
//        List<UniqueViewDTO> uniqueViewList = uniqueViewService.listUniqueViews();
//        // 查询文章统计
//        List<ArticleStatisticsDTO> articleStatisticsList = articleMapper.listArticleStatistics();
//        // 查询分类数据
//        List<CategoryDTO> categoryDTOList = categoryMapper.listCategoryDTO();
//        // 查询标签数据
//        List<TagDTO> tagDTOList = BeanCopyUtils.copyList(tagMapper.selectList(null), TagDTO.class);
//        // 查询redis访问量前五的文章
//        Map<Object, Double> articleMap = redisService.zReverseRangeWithScore(ARTICLE_VIEWS_COUNT, 0, 4);
//        BlogBackInfoDTO blogBackInfoDTO = BlogBackInfoDTO.builder()
//                .articleStatisticsList(articleStatisticsList)
//                .tagDTOList(tagDTOList)
//                .viewsCount(viewsCount)
//                .messageCount(messageCount)
//                .userCount(userCount)
//                .articleCount(articleCount)
//                .categoryDTOList(categoryDTOList)
//                .uniqueViewDTOList(uniqueViewList)
//                .build();
//        if (CollectionUtils.isNotEmpty(articleMap)) {
//            // 查询文章排行
//            List<ArticleRankDTO> articleRankDTOList = listArticleRank(articleMap);
//            blogBackInfoDTO.setArticleRankDTOList(articleRankDTOList);
//        }
//        return blogBackInfoDTO;
        return null;
    }


    @Override
    public void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO) {
        // 修改网站配置
        WebsiteConfig websiteConfig = WebsiteConfig.builder()
                .id(1)
                .config(JSON.toJSONString(websiteConfigVO))
                .build();
        websiteConfigMapper.updateById(websiteConfig);
        // 删除缓存
        RedisUtils.deleteObject(WEBSITE_CONFIG);
    }

    @Override
    public WebsiteConfigVO getWebsiteConfig() {
        WebsiteConfigVO websiteConfigVO;
        // 获取缓存数据
        Object websiteConfig = RedisUtils.getCacheObject(WEBSITE_CONFIG);
        if (Objects.nonNull(websiteConfig)) {
            websiteConfigVO = JSON.parseObject(websiteConfig.toString(), WebsiteConfigVO.class);
        } else {
            // 从数据库中加载
            String config = websiteConfigMapper.selectById(DEFAULT_CONFIG_ID).getConfig();
            websiteConfigVO = JSON.parseObject(config, WebsiteConfigVO.class);
            RedisUtils.setCacheObject(WEBSITE_CONFIG, config);
        }
        return websiteConfigVO;
    }

    @Override
    public String getAbout() {
        Object value = RedisUtils.getCacheObject(ABOUT);
        return Objects.nonNull(value) ? value.toString() : "";
    }

    @Override
    public void updateAbout(BlogInfoVO blogInfoVO) {
        RedisUtils.setCacheObject(ABOUT, blogInfoVO.getAboutContent());
    }

    @Override
    public void report() {
        // 获取ip
        String ipAddress = IpUtils.getIpAddress(request);
        // 获取访问设备
        UserAgent userAgent = IpUtils.getUserAgent(request);
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        // 生成唯一用户标识
        String uuid = ipAddress + browser.getName() + operatingSystem.getName();
        String md5 = DigestUtils.md5DigestAsHex(uuid.getBytes());
//        // 判断是否访问
//        if (!redisService.sIsMember(UNIQUE_VISITOR, md5)) {
//            // 统计游客地域分布
//            String ipSource = IpUtils.getIpSource(ipAddress);
//            if (StringUtils.isNotBlank(ipSource)) {
//                ipSource = ipSource.substring(0, 2)
//                        .replaceAll(PROVINCE, "")
//                        .replaceAll(CITY, "");
//                redisService.hIncr(VISITOR_AREA, ipSource, 1L);
//            } else {
//                redisService.hIncr(VISITOR_AREA, UNKNOWN, 1L);
//            }
//            // 访问量+1
//            redisService.incr(BLOG_VIEWS_COUNT, 1);
//            // 保存唯一标识
//            redisService.sAdd(UNIQUE_VISITOR, md5);
//        }
    }

    /**
     * 查询文章排行
     *
     * @param articleMap 文章信息
     * @return {@link List<ArticleRankDTO>} 文章排行
     */
    private List<ArticleRankDTO> listArticleRank(Map<Object, Double> articleMap) {
        // 提取文章id
        List<Integer> articleIdList = new ArrayList<>(articleMap.size());
        articleMap.forEach((key, value) -> articleIdList.add((Integer) key));
        // 查询文章信息
        return articleMapper.selectList(new LambdaQueryWrapper<Article>()
                        .select(Article::getId, Article::getArticleTitle)
                        .in(Article::getId, articleIdList))
                .stream().map(article -> ArticleRankDTO.builder()
                        .articleTitle(article.getArticleTitle())
                        .viewsCount(articleMap.get(article.getId()).intValue())
                        .build())
                .sorted(Comparator.comparingInt(ArticleRankDTO::getViewsCount).reversed())
                .collect(Collectors.toList());
    }

}
