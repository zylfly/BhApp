package com.zyl.beheapp.widget.recyclerview


interface MultipleType<in T> {
    fun getLayoutId(item: T, position: Int): Int
}
