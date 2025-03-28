package com.encryption.algorithm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName OcpxBackFromTableDTO
 * @Description TODO
 * @date 2025/3/27 11:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OcpxBackFromTableDTO {

    /** 二级渠道 */
    @JsonProperty("sec_channel_media")
    private String secChannelMedia;

    /** 网才城市名称 */
    @JsonProperty("ehire_cityname")
    private String ehireCityName;

    @JsonProperty("pt")
    private String pt;
    /** 网才城市名称 */
    @JsonProperty("etl_time_c")
    private String etlTimeC;

    @JsonProperty("ehire_ctm_auth_success")
    private String ehireCtmAuthSuccess;

    @JsonProperty("deeplinkid")
    private String deepLinkId;

    /** 网才用户id*/
    @JsonProperty("hraccid")
    private String hrAccId;

    /** 网才用户名*/
    @JsonProperty("ehire_cname")
    private String ehireCname;

    /** 用户设备，1：新，0：老*/
    @JsonProperty("uuid_type")
    private String uuidType;

    /** 网才行业*/
    @JsonProperty("ehire_industrytype")
    private String ehireIndustrytype;

    /** uuid*/
    @JsonProperty("uuid")
    private String uuId;

    /** C端用户id*/
    @JsonProperty("accountid")
    private String accountId;

    /** deeplink部门名称*/
    @JsonProperty("deeplink_cooperator_name")
    private String deeplinkCooperatorName;

    /** C端用户工作经验评分*/
    @JsonProperty("c_work_exp_star")
    private String cWorkExpStar;

    /** 用户类型，1：新用户，0：老用户*/
    @JsonProperty("user_type")
    private String userType;

    /** C端用户期望职能*/
    @JsonProperty("c_expect_func_one")
    private String cExpectFuncOne;

    /** C端用户身份*/
    @JsonProperty("c_personas")
    private String cPersonas;

    //TODO
    @JsonProperty("update_type")
    private String updateType;

    /** 网才地区*/
    @JsonProperty("ehire_province")
    private String ehireProvince;

    /** C端用户性别*/
    @JsonProperty("c_sex")
    private String cSex;

    /** 网才ctmid类型*/
    @JsonProperty("ehireCtmidType")
    private String ehire_ctmid_type;

    /** C端用户城市*/
    @JsonProperty("c_area_city")
    private String cAreaCity;

    /** 版本*/
    @JsonProperty("version")
    private String version;

    //todo
    @JsonProperty("advertiseid")
    private String advertiseId;


    /** 最高学历*/
    @JsonProperty("c_b_topschoolhierarchy")
    private String cBTopSchoolHierarchy;

    /** C端城市*/
    @JsonProperty("c_area")
    private String cArea;

    /** 来源*/
    @JsonProperty("fromdomain")
    private String fromDomain;

    //todo发出有效消息
    @JsonProperty("c_imchat_daily")
    private String cImchatDaily;

    /** ctmid对应公司类型*/
    @JsonProperty("ehire_companytype")
    private String ehireCompanytype;

    /** 广告计划id*/
    @JsonProperty("aid")
    private String aid;

    /** 广告组id*/
    @JsonProperty("campaigned")
    private String campaigned;

    /** C端用户行业*/
    @JsonProperty("c_top_industry")
    private String cTopIndustry;

    /** 二级渠道code */
    @JsonProperty("sec_channel_code")
    private String secChannelCode;

    /** 基本信息星级*/
    @JsonProperty("c_base_star")
    private String cBaseStar;

    /** C端用户第一份简历*/
    @JsonProperty("c_first_resume")
    private String cFirstResume;

    //todo
    @JsonProperty("etl_time")
    private String etlTime;

    /** 媒体二级渠道*/
    @JsonProperty("sec_channel_first_name")
    private String secChannelFirstName;

    /** 城市*/
    @JsonProperty("c_area_prov")
    private String cAreaProv;

    /** 发布职位*/
    @JsonProperty("ehire_publish")
    private String ehirePublish;

    /** deeplink部门*/
    @JsonProperty("deeplink_depart_name")
    private String deeplinkDepartName;

    /** 生日*/
    @JsonProperty("c_birthday")
    private String cBirthday;

    /** ctmid*/
    @JsonProperty("ehire_ctmid")
    private String ehireCtmid;

    //todo
    @JsonProperty("etl_time_ehire")
    private String etlTimeEhire;

    /** C端用户最高职能*/
    @JsonProperty("c_top_func_type")
    private String cTopFuncType;

    /** C端用户注册*/
    @JsonProperty("c_register")
    private String cRegister;

    /** C端用户工作年限*/
    @JsonProperty("c_workyear")
    private String cWorkyear;

    /** 求职意向*/
    @JsonProperty("c_desire_star")
    private String cDesireStar;

    //todo
    @JsonProperty("ehire_is_hr_auth_success")
    private String ehireIsHrAuthSuccess;

    /** 部门*/
    @JsonProperty("department")
    private String department;

    /** C端用户查看职位详情*/
    @JsonProperty("c_job_detail")
    private String cJobDetail;

    /** oaid*/
    @JsonProperty("oaid")
    private String oaid;

    /** 一级渠道名称 */
    @JsonProperty("first_channel_name")
    private String firstChannelName;

    /** 工作经验（星级）*/
    @JsonProperty("c_edu_exp_star")
    private String cEduExpStar;

    /** 是否创建会员*/
    @JsonProperty("ehire_register")
    private String ehireRegister;

    /** 投递职位*/
    @JsonProperty("c_job_apply")
    private String cJobApply;

    /** 广告创意id*/
    @JsonProperty("creativeid")
    private String creativeId;

    /** 支付订单*/
    @JsonProperty("ehirePay")
    private String ehirePay;

    /** 软件包渠道*/
    @JsonProperty("pkg_name")
    private String pkgName;

    /** 会员账号状态*/
    @JsonProperty("ehire_hraccid_type")
    private String ehireHrAccidType;

    /** 软件包*/
    @JsonProperty("partner")
    private String partner;

    //todo
    @JsonProperty("etl_time_base")
    private String etlTimeBase;

    /** 职位搜索*/
    @JsonProperty("c_search")
    private String cSearch;

    //todo
    @JsonProperty("c_degree_intention")
    private String cDegreeIntention;

    /** ctmid对应公司规模*/
    @JsonProperty("ehire_companysize")
    private String ehireCompanysize;


    /** ctmid对应公司规模*/
    @JsonProperty("checkid")
    private String checkId;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("openId")
    private String openId;

    @JsonProperty("unionId")
    private String unionId;

    /**
     * 广点通小程序是用type来控制事件行为，login为注册，resume为简历
     */
    @JsonProperty("type")
    private String type;

    @JsonProperty("traceData")
    public String traceData;
}