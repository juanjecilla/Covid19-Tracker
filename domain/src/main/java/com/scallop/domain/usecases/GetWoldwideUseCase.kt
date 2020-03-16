package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.common.Transformer
import com.scallop.domain.entities.WorldwideEntity
import com.scallop.domain.repositories.CovidRepository
import io.reactivex.Observable

class GetWoldwideUseCase(
    transformer: Transformer<WorldwideEntity>,
    private val repositories: CovidRepository
) : BaseUseCase<WorldwideEntity>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<WorldwideEntity> {
        return repositories.getWorldwideInfo()
    }

    fun getWorldwideInfo(): Observable<WorldwideEntity> {
        val data = HashMap<String, Long>()
        return observable(data)
    }
}