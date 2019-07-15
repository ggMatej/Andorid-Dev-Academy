package hr.ferit.brunozoric.taskie.di

import hr.ferit.brunozoric.taskie.domain.domain.deletingtasks.DeleteTaskUseCase
import hr.ferit.brunozoric.taskie.domain.domain.deletingtasks.DeleteTaskUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.domain.edittask.EditTaskUseCase
import hr.ferit.brunozoric.taskie.domain.domain.edittask.EditTaskUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.domain.gettask.GetTaskUseCase
import hr.ferit.brunozoric.taskie.domain.domain.gettask.GetTaskUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.domain.gettingtasks.GetTasksUseCase
import hr.ferit.brunozoric.taskie.domain.domain.gettingtasks.GetTasksUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.domain.login.LoginUseCase
import hr.ferit.brunozoric.taskie.domain.domain.login.LoginUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.domain.register.RegisterUseCase
import hr.ferit.brunozoric.taskie.domain.domain.register.RegisterUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.domain.savetask.SaveTaskUseCase
import hr.ferit.brunozoric.taskie.domain.domain.savetask.SaveTaskUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<LoginUseCase> { LoginUseCaseImpl(get()) }
    factory<RegisterUseCase> {RegisterUseCaseImpl(get())}
    factory<GetTasksUseCase> { GetTasksUseCaseImpl(get()) }
    factory<SaveTaskUseCase> { SaveTaskUseCaseImpl(get()) }
    factory<GetTaskUseCase> { GetTaskUseCaseImpl(get()) }
    factory<EditTaskUseCase> { EditTaskUseCaseImpl(get()) }
    factory<DeleteTaskUseCase> { DeleteTaskUseCaseImpl(get())}
}