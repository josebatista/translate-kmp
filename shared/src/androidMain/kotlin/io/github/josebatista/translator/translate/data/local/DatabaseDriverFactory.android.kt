package io.github.josebatista.translator.translate.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import io.github.josebatista.translator.core.domain.util.CommonConstants
import io.github.josebatista.translator.database.TranslateDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            schema = TranslateDatabase.Schema,
            context = context,
            name = CommonConstants.DATABASE_DRIVER_NAME
        )
    }
}
