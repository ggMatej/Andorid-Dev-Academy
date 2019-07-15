package hr.ferit.brunozoric.taskie.di

import hr.ferit.brunozoric.taskie.presentation.*
import hr.ferit.brunozoric.taskie.ui.activities.login.LoginContract
import hr.ferit.brunozoric.taskie.ui.activities.register.RegisterContract
import hr.ferit.brunozoric.taskie.ui.fragments.addtaskfragmentdialog.AddTaskFragmentDialogContract
import hr.ferit.brunozoric.taskie.ui.fragments.taskdetailfragment.TaskDetailsFragmentContract
import hr.ferit.brunozoric.taskie.ui.fragments.taskfragment.SwipeToDeleteCallbackContract
import hr.ferit.brunozoric.taskie.ui.fragments.taskfragment.TaskFragmentContract
import hr.ferit.brunozoric.taskie.ui.fragments.updatetaskfragmentdialog.UpdateTaskFragmentDialogContract
import org.koin.dsl.module

val presenterModule = module {
    factory<LoginContract.Presenter> {LoginPresenter(get())}
    factory<RegisterContract.Presenter> { RegisterPresenter(get()) }
    factory<TaskFragmentContract.Presenter> { TaskFragmentPresenter(get()) }
    factory<AddTaskFragmentDialogContract.Presenter> { AddTaskFragmentDialogPresenter(get()) }
    factory<TaskDetailsFragmentContract.Presenter> { TaskDetailsFragmentPresenter(get()) }
    factory<UpdateTaskFragmentDialogContract.Presenter> { UpdateTaskFragmentDialogPresenter(get(), get()) }
    factory<SwipeToDeleteCallbackContract.Presenter> { SwipeToDeleteCallbackPresenter(get()) }
}