package com.kaw.feature_favorite_impl.data.repository

import com.kaw.core_network_api.domain.repository.CachedRepository
import com.kaw.core_network_api.domain.source.RemoteDataSource
import com.kaw.core_utils.DateUtil
import com.kaw.feature_favorite_api.domain.models.Address
import com.kaw.feature_favorite_api.domain.models.Experience
import com.kaw.feature_favorite_api.domain.models.FavoriteScreenData
import com.kaw.feature_favorite_api.domain.models.Salary
import com.kaw.feature_favorite_api.domain.models.Vacancy
import com.kaw.feature_favorite_api.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val cachedRepository: CachedRepository
) : FavoriteRepository {

    override fun getFavoriteScreenData(): Flow<FavoriteScreenData> =
        cachedRepository.getResponse().map { response ->
            FavoriteScreenData(
                vacancies = response.vacancies.filter {
                    it.isFavorite
                }.map { vacancyDto ->
                    Vacancy(
                        id = vacancyDto.id,
                        lookingNumber = vacancyDto.lookingNumber,
                        title = vacancyDto.title,
                        address = Address(
                            town = vacancyDto.address.town,
                            street = vacancyDto.address.street,
                            house = vacancyDto.address.house
                        ),
                        company = vacancyDto.company,
                        experience = Experience(
                            previewText = vacancyDto.experience.previewText,
                            text = vacancyDto.experience.text
                        ),
                        publishedDate = DateUtil.formatPublishedDate(vacancyDto.publishedDate),
                        isFavorite = vacancyDto.isFavorite,
                        salary = Salary(
                            short = vacancyDto.salary.short,
                            full = vacancyDto.salary.full
                        ),
                        schedules = vacancyDto.schedules,
                        description = vacancyDto.description,
                        responsibilities = vacancyDto.responsibilities,
                        questions = vacancyDto.questions
                    )
                }
            )
        }


    override fun updateFavorite(vacancyId: String, isFavorite: Boolean): Flow<Unit> = flow {
        //Опять же, будет противоречие ТЗ, если я в локалку закину
        emit(Unit)
    }
}