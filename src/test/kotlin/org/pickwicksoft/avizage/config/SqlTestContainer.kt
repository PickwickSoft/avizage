package org.pickwicksoft.avizage.config

import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.testcontainers.containers.JdbcDatabaseContainer

interface SqlTestContainer : InitializingBean, DisposableBean {
    fun getTestContainer(): JdbcDatabaseContainer<*>
}
