package com.kaw.feature_main_impl.data.repository

import com.kaw.core_db_api.domain.entity.FavoriteVacancyEntity
import com.kaw.core_db_api.domain.source.LocalDataSource
import com.kaw.core_network_api.domain.repository.CachedRepository
import com.kaw.core_utils.DateUtil
import com.kaw.feature_main_api.domain.models.Address
import com.kaw.feature_main_api.domain.models.Button
import com.kaw.feature_main_api.domain.models.Experience
import com.kaw.feature_main_api.domain.models.MainScreenData
import com.kaw.feature_main_api.domain.models.Offer
import com.kaw.feature_main_api.domain.models.Salary
import com.kaw.feature_main_api.domain.models.Vacancy
import com.kaw.feature_main_api.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val cachedRepository: CachedRepository,
    private val localDataSource: LocalDataSource
) : MainRepository {

    override fun getMainScreenData(): Flow<MainScreenData> =
        cachedRepository.getResponse()
            .combine(localDataSource.getAll()) { response, localFavorites ->
                val vacancies = response.vacancies.map { vacancyDto ->
                    val localFavorite = localFavorites.find { it.id == vacancyDto.id }
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
                        isFavorite = localFavorite?.isFavorite ?: vacancyDto.isFavorite,
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
                MainScreenData(
                    offers = response.offers.map { offerDto ->
                        Offer(
                            id = offerDto.id,
                            title = offerDto.title,
                            link = offerDto.link,
                            button = offerDto.button?.let { buttonDto ->
                                Button(buttonDto.text)
                            }
                        )
                    },
                    vacancies = vacancies
                )
            }


    override fun updateFavorite(vacancyId: String, isFavorite: Boolean): Flow<Unit> = flow {
        localDataSource.insert(FavoriteVacancyEntity(vacancyId, isFavorite))
        emit(Unit)
    }
}