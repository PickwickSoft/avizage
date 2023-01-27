package org.pickwicksoft.avizage.config

// Regex for acceptable logins
const val LOGIN_REGEX: String = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$"
const val SYSTEM_ACCOUNT: String = "system"
const val DEFAULT_LANGUAGE: String = "en"
const val PHONE_REGEX: String = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}\$"
const val IBAN_REGEX: String = "/\\b[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?!(?:[ ]?[0-9]){3})(?:[ ]?[0-9]{1,2})?\\b/gm"
