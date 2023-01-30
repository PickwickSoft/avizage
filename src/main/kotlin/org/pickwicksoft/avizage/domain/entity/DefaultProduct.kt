package org.pickwicksoft.avizage.domain.entity

import javax.persistence.*

@Entity
@Table(name = "default_product")
class DefaultProduct(
    @OneToOne
    val product: Product,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
)
