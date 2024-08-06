package nazar.hr.myfinances.domain.errors

sealed class DataBaseErrors(error: String) : Throwable(error) {
    class UniqueConstraintError(error: String) : DataBaseErrors(error)
    class UnexpectedError(error: String) : DataBaseErrors(error)
}