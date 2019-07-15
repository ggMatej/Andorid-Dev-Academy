package osc.androiddevacademy.movieapp.ui.base

interface BasePresenter<T>{
    fun setView(view: T)
}