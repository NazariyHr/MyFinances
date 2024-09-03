package nazar.hr.myfinances.domain.use_cases.currencies

import nazar.hr.myfinances.domain.repository.CurrencyRepository
import javax.inject.Inject

class ChangeCurrencyIsMainUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {
    suspend operator fun invoke(currencyId: Int, isMain: Boolean) {
        if (isMain) {
            currencyRepository.setAsMainCurrency(currencyId)
        } else {
            currencyRepository.unsetAsMainCurrency(currencyId)
        }
    }
}