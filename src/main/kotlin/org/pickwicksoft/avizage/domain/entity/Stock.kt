package org.pickwicksoft.avizage.domain.entity

import javax.persistence.*

@Entity
@Table(name = "stock")
class Stock(

    @ManyToOne
    val product: Product,

    @ManyToOne
    val storage: Storage,

    @Column(nullable = false)
    val quantity: Double,

    @Column(nullable = false)
    val purchasePrice: Double,

    @Column(nullable = false)
    val salePrice: Double,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
)
