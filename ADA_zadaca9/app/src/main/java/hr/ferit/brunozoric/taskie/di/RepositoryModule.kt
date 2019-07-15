package hr.ferit.brunozoric.taskie.di

import hr.ferit.brunozoric.taskie.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    factory<LoginRepository> { LoginRepositoryImpl(get()) }
    factory<RegisterRepository>{ RegisterRepositoryImpl(get()) }
    factory<GetTasksRepository> { GetTasksRepositoryImpl(get()) }
    factory<SaveTaskRepository> { SaveTaskRepositoryImpl(get()) }
    factory<GetTaskRepository> { GetTaskRepositoryImpl(get()) }
    factory<EditTaskRepository> { EditTaskRepositoryImpl(get()) }
    factory<DeleteTaskRepository> { DeleteTaskRepositoryImpl(get()) }
}