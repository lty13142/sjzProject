package com.crcm.starter.cloud.swagger.properties;

import com.github.xiaoymin.knife4j.core.enums.OpenAPILanguageEnums;

/**
 * @ClassName ApiDocSetting
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2023/1/31
 **/
public class ApiDocSetting {
    private Integer customCode = 200;
    private OpenAPILanguageEnums language;
    private boolean enableSwaggerModels;
    private String swaggerModelName;
    private boolean enableReloadCacheParameter;
    private boolean enableAfterScript;
    private boolean enableDocumentManage;
    private boolean enableVersion;
    private boolean enableRequestCache;
    private boolean enableFilterMultipartApis;
    private String enableFilterMultipartApiMethodType;
    private boolean enableHost;
    private String enableHostText;
    private boolean enableDynamicParameter;
    private boolean enableDebug;
    private boolean enableFooter;
    private boolean enableFooterCustom;
    private String footerCustomContent;
    private boolean enableSearch;
    private boolean enableOpenApi;
    private boolean enableHomeCustom;
    private String homeCustomLocation;
    private boolean enableGroup;
    private boolean enableResponseCode;

    public ApiDocSetting() {
        this.language = OpenAPILanguageEnums.ZH_CN;
        this.enableSwaggerModels = true;
        this.swaggerModelName = "Swagger Models";
        this.enableReloadCacheParameter = false;
        this.enableAfterScript = true;
        this.enableDocumentManage = true;
        this.enableVersion = false;
        this.enableRequestCache = true;
        this.enableFilterMultipartApis = false;
        this.enableFilterMultipartApiMethodType = "POST";
        this.enableHost = false;
        this.enableHostText = "";
        this.enableDynamicParameter = false;
        this.enableDebug = true;
        this.enableFooter = true;
        this.enableFooterCustom = false;
        this.enableSearch = true;
        this.enableOpenApi = true;
        this.enableHomeCustom = false;
        this.enableGroup = true;
        this.enableResponseCode = true;
    }

    public Integer getCustomCode() {
        return this.customCode;
    }

    public OpenAPILanguageEnums getLanguage() {
        return this.language;
    }

    public boolean isEnableSwaggerModels() {
        return this.enableSwaggerModels;
    }

    public String getSwaggerModelName() {
        return this.swaggerModelName;
    }

    public boolean isEnableReloadCacheParameter() {
        return this.enableReloadCacheParameter;
    }

    public boolean isEnableAfterScript() {
        return this.enableAfterScript;
    }

    public boolean isEnableDocumentManage() {
        return this.enableDocumentManage;
    }

    public boolean isEnableVersion() {
        return this.enableVersion;
    }

    public boolean isEnableRequestCache() {
        return this.enableRequestCache;
    }

    public boolean isEnableFilterMultipartApis() {
        return this.enableFilterMultipartApis;
    }

    public String getEnableFilterMultipartApiMethodType() {
        return this.enableFilterMultipartApiMethodType;
    }

    public boolean isEnableHost() {
        return this.enableHost;
    }

    public String getEnableHostText() {
        return this.enableHostText;
    }

    public boolean isEnableDynamicParameter() {
        return this.enableDynamicParameter;
    }

    public boolean isEnableDebug() {
        return this.enableDebug;
    }

    public boolean isEnableFooter() {
        return this.enableFooter;
    }

    public boolean isEnableFooterCustom() {
        return this.enableFooterCustom;
    }

    public String getFooterCustomContent() {
        return this.footerCustomContent;
    }

    public boolean isEnableSearch() {
        return this.enableSearch;
    }

    public boolean isEnableOpenApi() {
        return this.enableOpenApi;
    }

    public boolean isEnableHomeCustom() {
        return this.enableHomeCustom;
    }

    public String getHomeCustomLocation() {
        return this.homeCustomLocation;
    }

    public boolean isEnableGroup() {
        return this.enableGroup;
    }

    public boolean isEnableResponseCode() {
        return this.enableResponseCode;
    }

    public void setCustomCode(Integer customCode) {
        this.customCode = customCode;
    }

    public void setLanguage(OpenAPILanguageEnums language) {
        this.language = language;
    }

    public void setEnableSwaggerModels(boolean enableSwaggerModels) {
        this.enableSwaggerModels = enableSwaggerModels;
    }

    public void setSwaggerModelName(String swaggerModelName) {
        this.swaggerModelName = swaggerModelName;
    }

    public void setEnableReloadCacheParameter(boolean enableReloadCacheParameter) {
        this.enableReloadCacheParameter = enableReloadCacheParameter;
    }

    public void setEnableAfterScript(boolean enableAfterScript) {
        this.enableAfterScript = enableAfterScript;
    }

    public void setEnableDocumentManage(boolean enableDocumentManage) {
        this.enableDocumentManage = enableDocumentManage;
    }

    public void setEnableVersion(boolean enableVersion) {
        this.enableVersion = enableVersion;
    }

    public void setEnableRequestCache(boolean enableRequestCache) {
        this.enableRequestCache = enableRequestCache;
    }

    public void setEnableFilterMultipartApis(boolean enableFilterMultipartApis) {
        this.enableFilterMultipartApis = enableFilterMultipartApis;
    }

    public void setEnableFilterMultipartApiMethodType(String enableFilterMultipartApiMethodType) {
        this.enableFilterMultipartApiMethodType = enableFilterMultipartApiMethodType;
    }

    public void setEnableHost(boolean enableHost) {
        this.enableHost = enableHost;
    }

    public void setEnableHostText(String enableHostText) {
        this.enableHostText = enableHostText;
    }

    public void setEnableDynamicParameter(boolean enableDynamicParameter) {
        this.enableDynamicParameter = enableDynamicParameter;
    }

    public void setEnableDebug(boolean enableDebug) {
        this.enableDebug = enableDebug;
    }

    public void setEnableFooter(boolean enableFooter) {
        this.enableFooter = enableFooter;
    }

    public void setEnableFooterCustom(boolean enableFooterCustom) {
        this.enableFooterCustom = enableFooterCustom;
    }

    public void setFooterCustomContent(String footerCustomContent) {
        this.footerCustomContent = footerCustomContent;
    }

    public void setEnableSearch(boolean enableSearch) {
        this.enableSearch = enableSearch;
    }

    public void setEnableOpenApi(boolean enableOpenApi) {
        this.enableOpenApi = enableOpenApi;
    }

    public void setEnableHomeCustom(boolean enableHomeCustom) {
        this.enableHomeCustom = enableHomeCustom;
    }

    public void setHomeCustomLocation(String homeCustomLocation) {
        this.homeCustomLocation = homeCustomLocation;
    }

    public void setEnableGroup(boolean enableGroup) {
        this.enableGroup = enableGroup;
    }

    public void setEnableResponseCode(boolean enableResponseCode) {
        this.enableResponseCode = enableResponseCode;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ApiDocSetting)) {
            return false;
        } else {
            ApiDocSetting other = (ApiDocSetting) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isEnableSwaggerModels() != other.isEnableSwaggerModels()) {
                return false;
            } else if (this.isEnableReloadCacheParameter() != other.isEnableReloadCacheParameter()) {
                return false;
            } else if (this.isEnableAfterScript() != other.isEnableAfterScript()) {
                return false;
            } else if (this.isEnableDocumentManage() != other.isEnableDocumentManage()) {
                return false;
            } else if (this.isEnableVersion() != other.isEnableVersion()) {
                return false;
            } else if (this.isEnableRequestCache() != other.isEnableRequestCache()) {
                return false;
            } else if (this.isEnableFilterMultipartApis() != other.isEnableFilterMultipartApis()) {
                return false;
            } else if (this.isEnableHost() != other.isEnableHost()) {
                return false;
            } else if (this.isEnableDynamicParameter() != other.isEnableDynamicParameter()) {
                return false;
            } else if (this.isEnableDebug() != other.isEnableDebug()) {
                return false;
            } else if (this.isEnableFooter() != other.isEnableFooter()) {
                return false;
            } else if (this.isEnableFooterCustom() != other.isEnableFooterCustom()) {
                return false;
            } else if (this.isEnableSearch() != other.isEnableSearch()) {
                return false;
            } else if (this.isEnableOpenApi() != other.isEnableOpenApi()) {
                return false;
            } else if (this.isEnableHomeCustom() != other.isEnableHomeCustom()) {
                return false;
            } else if (this.isEnableGroup() != other.isEnableGroup()) {
                return false;
            } else if (this.isEnableResponseCode() != other.isEnableResponseCode()) {
                return false;
            } else {
                label137:
                {
                    Object this$customCode = this.getCustomCode();
                    Object other$customCode = other.getCustomCode();
                    if (this$customCode == null) {
                        if (other$customCode == null) {
                            break label137;
                        }
                    } else if (this$customCode.equals(other$customCode)) {
                        break label137;
                    }

                    return false;
                }

                Object this$language = this.getLanguage();
                Object other$language = other.getLanguage();
                if (this$language == null) {
                    if (other$language != null) {
                        return false;
                    }
                } else if (!this$language.equals(other$language)) {
                    return false;
                }

                label123:
                {
                    Object this$swaggerModelName = this.getSwaggerModelName();
                    Object other$swaggerModelName = other.getSwaggerModelName();
                    if (this$swaggerModelName == null) {
                        if (other$swaggerModelName == null) {
                            break label123;
                        }
                    } else if (this$swaggerModelName.equals(other$swaggerModelName)) {
                        break label123;
                    }

                    return false;
                }

                Object this$enableFilterMultipartApiMethodType = this.getEnableFilterMultipartApiMethodType();
                Object other$enableFilterMultipartApiMethodType = other.getEnableFilterMultipartApiMethodType();
                if (this$enableFilterMultipartApiMethodType == null) {
                    if (other$enableFilterMultipartApiMethodType != null) {
                        return false;
                    }
                } else if (!this$enableFilterMultipartApiMethodType.equals(other$enableFilterMultipartApiMethodType)) {
                    return false;
                }

                Object this$enableHostText = this.getEnableHostText();
                Object other$enableHostText = other.getEnableHostText();
                if (this$enableHostText == null) {
                    if (other$enableHostText != null) {
                        return false;
                    }
                } else if (!this$enableHostText.equals(other$enableHostText)) {
                    return false;
                }

                label102:
                {
                    Object this$footerCustomContent = this.getFooterCustomContent();
                    Object other$footerCustomContent = other.getFooterCustomContent();
                    if (this$footerCustomContent == null) {
                        if (other$footerCustomContent == null) {
                            break label102;
                        }
                    } else if (this$footerCustomContent.equals(other$footerCustomContent)) {
                        break label102;
                    }

                    return false;
                }

                Object this$homeCustomLocation = this.getHomeCustomLocation();
                Object other$homeCustomLocation = other.getHomeCustomLocation();
                if (this$homeCustomLocation == null) {
                    if (other$homeCustomLocation != null) {
                        return false;
                    }
                } else if (!this$homeCustomLocation.equals(other$homeCustomLocation)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ApiDocSetting;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + (this.isEnableSwaggerModels() ? 79 : 97);
        result = result * 59 + (this.isEnableReloadCacheParameter() ? 79 : 97);
        result = result * 59 + (this.isEnableAfterScript() ? 79 : 97);
        result = result * 59 + (this.isEnableDocumentManage() ? 79 : 97);
        result = result * 59 + (this.isEnableVersion() ? 79 : 97);
        result = result * 59 + (this.isEnableRequestCache() ? 79 : 97);
        result = result * 59 + (this.isEnableFilterMultipartApis() ? 79 : 97);
        result = result * 59 + (this.isEnableHost() ? 79 : 97);
        result = result * 59 + (this.isEnableDynamicParameter() ? 79 : 97);
        result = result * 59 + (this.isEnableDebug() ? 79 : 97);
        result = result * 59 + (this.isEnableFooter() ? 79 : 97);
        result = result * 59 + (this.isEnableFooterCustom() ? 79 : 97);
        result = result * 59 + (this.isEnableSearch() ? 79 : 97);
        result = result * 59 + (this.isEnableOpenApi() ? 79 : 97);
        result = result * 59 + (this.isEnableHomeCustom() ? 79 : 97);
        result = result * 59 + (this.isEnableGroup() ? 79 : 97);
        result = result * 59 + (this.isEnableResponseCode() ? 79 : 97);
        Object $customCode = this.getCustomCode();
        result = result * 59 + ($customCode == null ? 43 : $customCode.hashCode());
        Object $language = this.getLanguage();
        result = result * 59 + ($language == null ? 43 : $language.hashCode());
        Object $swaggerModelName = this.getSwaggerModelName();
        result = result * 59 + ($swaggerModelName == null ? 43 : $swaggerModelName.hashCode());
        Object $enableFilterMultipartApiMethodType = this.getEnableFilterMultipartApiMethodType();
        result = result * 59 + ($enableFilterMultipartApiMethodType == null ? 43 : $enableFilterMultipartApiMethodType.hashCode());
        Object $enableHostText = this.getEnableHostText();
        result = result * 59 + ($enableHostText == null ? 43 : $enableHostText.hashCode());
        Object $footerCustomContent = this.getFooterCustomContent();
        result = result * 59 + ($footerCustomContent == null ? 43 : $footerCustomContent.hashCode());
        Object $homeCustomLocation = this.getHomeCustomLocation();
        result = result * 59 + ($homeCustomLocation == null ? 43 : $homeCustomLocation.hashCode());
        return result;
    }

    public String toString() {
        return "ApiDocSetting(customCode=" + this.getCustomCode() + ", language=" + this.getLanguage() + ", enableSwaggerModels=" + this.isEnableSwaggerModels() + ", swaggerModelName=" + this.getSwaggerModelName() + ", enableReloadCacheParameter=" + this.isEnableReloadCacheParameter() + ", enableAfterScript=" + this.isEnableAfterScript() + ", enableDocumentManage=" + this.isEnableDocumentManage() + ", enableVersion=" + this.isEnableVersion() + ", enableRequestCache=" + this.isEnableRequestCache() + ", enableFilterMultipartApis=" + this.isEnableFilterMultipartApis() + ", enableFilterMultipartApiMethodType=" + this.getEnableFilterMultipartApiMethodType() + ", enableHost=" + this.isEnableHost() + ", enableHostText=" + this.getEnableHostText() + ", enableDynamicParameter=" + this.isEnableDynamicParameter() + ", enableDebug=" + this.isEnableDebug() + ", enableFooter=" + this.isEnableFooter() + ", enableFooterCustom=" + this.isEnableFooterCustom() + ", footerCustomContent=" + this.getFooterCustomContent() + ", enableSearch=" + this.isEnableSearch() + ", enableOpenApi=" + this.isEnableOpenApi() + ", enableHomeCustom=" + this.isEnableHomeCustom() + ", homeCustomLocation=" + this.getHomeCustomLocation() + ", enableGroup=" + this.isEnableGroup() + ", enableResponseCode=" + this.isEnableResponseCode() + ")";
    }
}
