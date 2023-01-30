package org.pickwicksoft.avizage.domain.entity

import javax.persistence.*

@Entity
@Table(name = "category")
class Category(

    @Column(nullable = false)
    val name: String,

    val iconName: String? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
)
