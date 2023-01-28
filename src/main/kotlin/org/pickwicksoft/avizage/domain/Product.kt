package org.pickwicksoft.avizage.domain

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "product")
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    var productId: Long? = null

    @field:Size(min = 13, max = 13)
    @Column(length = 13)
    var code: Long? = null

    @field:Size(max = 50)
    @Column(length = 50)
    var name: String? = null

    @field:Size(max = 100)
    @Column(length = 100)
    var description: String? = null

    @field:Size(max = 5)
    @Column(length = 5)
    var unit: String? = null

    @OneToMany(mappedBy = "product")
    var stocks: MutableSet<Stock> = mutableSetOf()
}
