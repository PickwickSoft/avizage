package org.pickwicksoft.avizage.domain.entity

import javax.persistence.*

@Entity
@Table(name = "default_category")
class DefaultCategory(
    @OneToOne
    val category: Category,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
)
