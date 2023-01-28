package org.pickwicksoft.avizage.domain

import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
@Table(name = "unit")
class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id", nullable = false)
    var unitId: Int? = null

    @field:NotNull
    @Column(length = 25, nullable = false)
    var name: String? = null

    @field:NotNull
    @Column(length = 5, nullable = false)
    var code: String? = null
}
