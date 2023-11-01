package org.arvarik.openai.core.api.common

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode

/**
 * Common test class to assert the serialization of a data class using Behavior Driven Testing Style
 *
 * @see [kotest BehaviorSpec Doc](https://kotest.io/docs/framework/testing-styles.html#behavior-spec)
 *
 * @param serializer The serializer for the type of the given data class instance
 * @param dataClassInstance The given data class instance to assert serialization for
 * @param expectedSerializedString The expected string serialization of the given data class instance
 * @param invalidSerializedString An invalid serialized string - should produce an error
 */
@Ignored
open class DataClassSerializationCommonTest<T>(
    serializer: KSerializer<T>,
    dataClassInstance: T,
    expectedSerializedString: String,
    invalidSerializedString: String,
) : BehaviorSpec({

        given("Data class instance") {
            `when`("Data class instance is serialized") {
                val dataClassInstanceSerialized = Json.encodeToString(serializer, dataClassInstance)
                then("return correct serialization") {
                    JSONAssert.assertEquals(
                        expectedSerializedString,
                        dataClassInstanceSerialized,
                        JSONCompareMode.NON_EXTENSIBLE,
                    )
                }

                and("Data class instance string is deserialized") {
                    val dataClassInstanceDeserialized = Json.decodeFromString(serializer, dataClassInstanceSerialized)
                    then("returns correct data class instance deserialized") {
                        dataClassInstanceDeserialized shouldBe dataClassInstance
                    }
                }
            }
        }

        given("Invalid serialized string") {
            `when`("Invalid data class instance string is deserialized") {
                then("throw SerializationException") {
                    shouldThrow<SerializationException> {
                        Json.decodeFromString(serializer, invalidSerializedString)
                    }
                }
            }
        }
    })
