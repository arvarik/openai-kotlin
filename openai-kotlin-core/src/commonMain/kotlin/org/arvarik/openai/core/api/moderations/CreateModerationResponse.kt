package org.arvarik.openai.core.api.moderations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse

/**
 * Response object for the /moderations endpoint
 *
 * Given an input text, outputs if the model classifies it as violating OpenAI's content policy
 *
 * @see [Create Moderation API](https://beta.openai.com/docs/api-reference/moderations/create)
 */
@Serializable
data class CreateModerationResponse(
    /**
     * Identifier for the moderation API call
     */
    val id: String,
    /**
     * The GPT-3 model used for moderation
     */
    val model: String,
    /**
     * The moderation results of the given input text
     */
    val results: List<ModerationResult>,
) : OpenAIResponse

/**
 * The moderation result of a given input text
 */
@Serializable
data class ModerationResult(
    val categories: Categories,
    @SerialName("category_scores")
    val categoryScores: CategoryScores,
    val flagged: Boolean,
)

/**
 * Currently available OpenAI moderation categories
 */
@Serializable
data class Categories(
    /**
     * Content that expresses, incites, or promotes hate based on race, gender, ethnicity, religion, nationality,
     * sexual orientation, disability status, or caste
     */
    val hate: Boolean,
    /**
     * Hateful content that also includes violence or serious harm towards the targeted group
     */
    @SerialName("hate/threatening")
    val hateAndThreatening: Boolean,
    /**
     * Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders
     */
    @SerialName("self-harm")
    val selfHarm: Boolean,
    /**
     * Content meant to arouse sexual excitement, such as the description of sexual activity, or that promotes sexual
     * services (excluding sex education and wellness)
     */
    val sexual: Boolean,
    /**
     * Sexual content that includes an individual who is under 18 years old
     */
    @SerialName("sexual/minors")
    val sexualAndMinors: Boolean,
    /**
     * Content that promotes or glorifies violence or celebrates the suffering or humiliation of others
     */
    val violence: Boolean,
    /**
     * Violent content that depicts death, violence, or serious physical injury in extreme graphic detail
     */
    @SerialName("violence/graphic")
    val violenceAndGraphic: Boolean,
)

/**
 * Confidence scores of the associated moderation categories
 *
 * See category descriptions in docs for [Categories]
 */
@Serializable
data class CategoryScores(
    val hate: Double,
    @SerialName("hate/threatening")
    val hateAndThreatening: Double,
    @SerialName("self-harm")
    val selfHarm: Double,
    val sexual: Double,
    @SerialName("sexual/minors")
    val sexualAndMinors: Double,
    val violence: Double,
    @SerialName("violence/graphic")
    val violenceAndGraphic: Double,
)
