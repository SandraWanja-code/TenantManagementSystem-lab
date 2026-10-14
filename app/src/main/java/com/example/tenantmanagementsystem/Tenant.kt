package com.example.tenantmanagementsystem

import java.io.Serializable

// Serializable is built into Java/Kotlin: no plugin needed
// ": Serializable" means "a Tenant can be packed up and sent
// to another screen". Safe Args needs this in Practical 3
data class Tenant(
    val name: String,
    val phone: String,
    val rent: String
) : Serializable {
    // Unchanged: the text shown by @{tenant.summary()}
    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent: KSh $rent"
    }
}