package dev.robit.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "USERS")
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open var id: Long? = null

    @Column(name = "username", nullable = false)
    open var username: String? = null

    @Column(name = "first_name")
    open var firstName: String? = null

    @Column(name = "last_name")
    open var lastName: String? = null
}
