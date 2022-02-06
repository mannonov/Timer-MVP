package com.mannonov.timermvp.base

interface BasePresenter<V> {
    fun attachView(view: V)
    fun detachView()
}