package io.github.josebatista.translator.translate.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.github.josebatista.translator.core.domain.util.CommonConstants
import io.github.josebatista.translator.database.TranslateDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            schema = TranslateDatabase.Schema,
            name = CommonConstants.DATABASE_DRIVER_NAME
        )
    }
}
