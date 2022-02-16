package com.example.mvvm_data_binding_demo.utils

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class DelegatesString : ReadWriteProperty<Any, String> {
    private var trimAppendedString = ""
    override fun getValue(thisRef: Any, property: KProperty<*>) = trimAppendedString
    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        trimAppendedString = if (trimAppendedString.length < 5) {
            "${value.trim()} is a String!"
        } else {
            value
        }
    }
}