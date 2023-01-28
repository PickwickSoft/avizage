package org.pickwicksoft.avizage.domain.entity

import javax.persistence.*

@Entity
@Table(name = "stock", uniqueConstraints = [ UniqueConstraint(columnNames = ["product", "storage"])])
class Stock (

    @ManyToOne
    val product: Product,

    @ManyToOne
    val storage: Storage,

    @Column(nullable = false)
    val quantity: Double,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
)
