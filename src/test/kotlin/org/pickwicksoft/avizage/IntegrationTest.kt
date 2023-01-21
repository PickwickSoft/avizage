package org.pickwicksoft.avizage

import org.pickwicksoft.avizage.config.AsyncSyncConfiguration
import org.pickwicksoft.avizage.config.EmbeddedSQL
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext

/**
 * Base composite annotation for integration tests.
 */
@kotlin.annotation.Target(AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@SpringBootTest(classes = [AvizageApp::class, AsyncSyncConfiguration::class])
@EmbeddedSQL
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
annotation class IntegrationTest
