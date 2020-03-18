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
    private val mEntityToDataMapper: CovidEntityDataMapper,
    private val mDataToEntityMapper: CovidDataEntityMapper
) : CovidDataSource {

    private val mDao: CovidDao = database.getCovidDao()

    override fun getWorldwideInfo(): Observable<WorldwideEntity> {
        return mDao.getWorldwideInfo().map { mDataToEntityMapper.mapToEntity(it) }
    }

    override fun getInfoByCountry(): Observable<List<CountryEntity>> {
        return mDao.getInfoByCountry().map { mDataToEntityMapper.mapToEntity(it) }
    }

    fun setWorldwideInfo(worldwideEntity: WorldwideEntity){
        mDao.clearWorldwideInfo()
        mDao.setWorldwideInfo(mEntityToDataMapper.mapToData(worldwideEntity))
    }

    fun setInfoByCountry(countryEntity: List<CountryEntity>){
        mDao.clearInfoByCountry()
        mDao.setInfoByCountry(mEntityToDataMapper.mapToData(countryEntity))
    }
}