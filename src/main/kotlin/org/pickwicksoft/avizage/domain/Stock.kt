package org.pickwicksoft.avizage.domain

import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
@Table(name = "stock", uniqueConstraints = [ UniqueConstraint(columnNames = ["product", "storage"])])
class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id", nullable = false)
    var stockId: Long? = null

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "storage_id")
    var storage: Storage? = null

    @field:NotNull
    @Column(nullable = false)
    var quantity: Int? = null
}
