package org.pickwicksoft.avizage.domain.entity

import javax.persistence.*

@Entity
@Table(name = "county")
class County (

    @Column(length = 12, nullable = false)
    val code: String,

    @Column(length = 40, nullable = false)
    val name: String,

    @ManyToOne
    val country: Country,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
