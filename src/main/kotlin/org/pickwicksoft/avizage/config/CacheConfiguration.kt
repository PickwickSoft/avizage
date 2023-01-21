package org.pickwicksoft.avizage.config

import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.ExpiryPolicyBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import org.ehcache.jsr107.Eh107Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer
import org.springframework.boot.info.BuildProperties
import org.springframework.boot.info.GitProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tech.jhipster.config.JHipsterProperties
import tech.jhipster.config.cache.PrefixedKeyGenerator
import java.time.Duration

@Configuration
@EnableCaching
class CacheConfiguration(
    @Autowired val gitProperties: GitProperties?,
    @Autowired val buildProperties: BuildProperties?,
    private val jHipsterProperties: JHipsterProperties
) {

    private val jcacheConfiguration: javax.cache.configuration.Configuration<Any, Any>

    init {
        val ehcache = jHipsterProperties.cache.ehcache

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(
                Any::class.java, Any::class.java,
                ResourcePoolsBuilder.heap(ehcache.maxEntries)
            )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.timeToLiveSeconds.toLong())))
                .build()
        )
    }

    @Bean
    fun cacheManagerCustomizer(): JCacheManagerCustomizer {
        return JCacheManagerCustomizer { cm ->
            createCache(cm, org.pickwicksoft.avizage.repository.UserRepository.USERS_BY_LOGIN_CACHE)
            createCache(cm, org.pickwicksoft.avizage.repository.UserRepository.USERS_BY_EMAIL_CACHE)
            // jhipster-needle-ehcache-add-entry
        }
    }

    private fun createCache(cm: javax.cache.CacheManager, cacheName: String) {
        val cache: javax.cache.Cache<Any, Any>? = cm.getCache(cacheName)
        if (cache != null) {
            cache.clear()
        } else {
            cm.createCache(cacheName, jcacheConfiguration)
        }
    }

    @Bean
    fun keyGenerator() = PrefixedKeyGenerator(gitProperties, buildProperties)
}
