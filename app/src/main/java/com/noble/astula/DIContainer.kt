package com.noble.astula

class DIContainer {
    private val classes = mutableMapOf<Class<*>, Any>()

    fun <T: Any> register(providedClass: Class<T>, impl: T) {
        classes[providedClass] = impl
    }

    fun <T: Any> resolve(providedClass: Class<T>): T {
        return classes[providedClass] as T
    }
}