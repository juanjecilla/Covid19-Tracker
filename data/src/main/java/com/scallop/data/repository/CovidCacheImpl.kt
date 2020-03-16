package com.scallop.data.repository

import com.scallop.data.db.CovidDao
import com.scallop.data.db.CovidDatabase
import com.scallop.data.mappers.CovidDataEntityMapper
import com.scallop.data.mappers.CovidEntityDataMapper
import com.scallop.domain.entities.CountryEntity
import com.scallop.domain.entities.WorldwideEntity
import io.reactivex.Observable

class CovidCacheImpl(
    database: CovidDatabase,
    private val entityToDataMapper: CovidEntityDataMapper,
    private val dataToEntityMapper: CovidDataEntityMapper
) : CovidDataSource {

    private val dao: CovidDao = database.getCovidDao()

//    override fun getMovieItems(): Observable<MovieSourcesEntity> {
//        return dao.getAllArticles().map {
//            dataToEntityMapper.mapToEntity(it)
//        }
//    }
//
//    override fun getMovieDetail(id: Long): Observable<WorldwideEntity> {
//        return dao.getMovieDetail(id).map { dataToEntityMapper.mapToEntity(it) }
//    }
//
//    fun saveArticles(it: MovieSourcesEntity) {
//        dao.clear()
//        dao.saveAllArticles(it.results.map { articles ->
//            entityToDataMapper.mapArticleToEntity(
//                articles
//            )
//        })
//    }

    override fun getWorldwideInfo(): Observable<WorldwideEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInfoByCountry(): Observable<List<CountryEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}