package com.machbees.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.machbees.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.machbees.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.machbees.domain.User.class.getName());
            createCache(cm, com.machbees.domain.Authority.class.getName());
            createCache(cm, com.machbees.domain.User.class.getName() + ".authorities");
            createCache(cm, com.machbees.domain.CategoryMetadata.class.getName());
            createCache(cm, com.machbees.domain.ProfileMaster.class.getName());
            createCache(cm, com.machbees.domain.ProjectTypeMaster.class.getName());
            createCache(cm, com.machbees.domain.ProjectSpecificationMaster.class.getName());
            createCache(cm, com.machbees.domain.ProjectCategoryMaster.class.getName());
            createCache(cm, com.machbees.domain.ProjectFeatureMaster.class.getName());
            createCache(cm, com.machbees.domain.ProjectRoleMaster.class.getName());
            createCache(cm, com.machbees.domain.SkillCategoryMaster.class.getName());
            createCache(cm, com.machbees.domain.SkillMaster.class.getName());
            createCache(cm, com.machbees.domain.UserMaster.class.getName());
            createCache(cm, com.machbees.domain.UserMaster.class.getName() + ".userlanguagedetails");
            createCache(cm, com.machbees.domain.UserPersonalDetails.class.getName());
            createCache(cm, com.machbees.domain.UserCompanyDetails.class.getName());
            createCache(cm, com.machbees.domain.UserLanguageDetails.class.getName());
            createCache(cm, com.machbees.domain.ProjectHdr.class.getName());
            createCache(cm, com.machbees.domain.ProjectHdr.class.getName() + ".projectattachmentdetails");
            createCache(cm, com.machbees.domain.ProjectHdr.class.getName() + ".projectfeaturedetails");
            createCache(cm, com.machbees.domain.ProjectHdr.class.getName() + ".projectroledetails");
            createCache(cm, com.machbees.domain.ProjectHdr.class.getName() + ".projectskilldetails");
            createCache(cm, com.machbees.domain.ProjectAttachmentDtl.class.getName());
            createCache(cm, com.machbees.domain.ProjectFeatureDtl.class.getName());
            createCache(cm, com.machbees.domain.ProjectRoleDtl.class.getName());
            createCache(cm, com.machbees.domain.ProjectSkillDtl.class.getName());
            createCache(cm, com.machbees.domain.ProjectBudgetDtl.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
