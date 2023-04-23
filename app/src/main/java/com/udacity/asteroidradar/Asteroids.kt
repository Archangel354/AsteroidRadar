package com.udacity.asteroidradar


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
internal data class Asteroids(
    val element_count: Int,
    val links: Links,
    val near_earth_objects: NearEarthObjects
) : Parcelable {
    @Parcelize
    internal data class Links(
        val next: String,
        val previous: String,
        val self: String
    ) : Parcelable

    @Parcelize
    internal data class NearEarthObjects(
        val `2022-02-05`: List<X20220205>,
        val `2022-02-06`: List<X20220206>
    ) : Parcelable {
        @Parcelize
        internal data class X20220205(
            val absolute_magnitude_h: Double,
            val close_approach_data: List<CloseApproachData>,
            val estimated_diameter: EstimatedDiameter,
            val id: String,
            val is_potentially_hazardous_asteroid: Boolean,
            val is_sentry_object: Boolean,
            val links: Links,
            val name: String,
            val nasa_jpl_url: String,
            val neo_reference_id: String
        ) : Parcelable {
            @Parcelize
            internal data class CloseApproachData(
                val close_approach_date: String,
                val close_approach_date_full: String,
                val epoch_date_close_approach: Long,
                val miss_distance: MissDistance,
                val orbiting_body: String,
                val relative_velocity: RelativeVelocity
            ) : Parcelable {
                @Parcelize
                internal data class MissDistance(
                    val astronomical: String,
                    val kilometers: String,
                    val lunar: String,
                    val miles: String
                ) : Parcelable

                @Parcelize
                internal data class RelativeVelocity(
                    val kilometers_per_hour: String,
                    val kilometers_per_second: String,
                    val miles_per_hour: String
                ) : Parcelable
            }

            @Parcelize
            internal data class EstimatedDiameter(
                val feet: Feet,
                val kilometers: Kilometers,
                val meters: Meters,
                val miles: Miles
            ) : Parcelable {
                @Parcelize
                internal data class Feet(
                    val estimated_diameter_max: Double,
                    val estimated_diameter_min: Double
                ) : Parcelable

                @Parcelize
                internal data class Kilometers(
                    val estimated_diameter_max: Double,
                    val estimated_diameter_min: Double
                ) : Parcelable

                @Parcelize
                internal data class Meters(
                    val estimated_diameter_max: Double,
                    val estimated_diameter_min: Double
                ) : Parcelable

                @Parcelize
                internal data class Miles(
                    val estimated_diameter_max: Double,
                    val estimated_diameter_min: Double
                ) : Parcelable
            }

            @Parcelize
            internal data class Links(
                val self: String
            ) : Parcelable
        }

        @Parcelize
        internal data class X20220206(
            val absolute_magnitude_h: Double,
            val close_approach_data: List<CloseApproachData>,
            val estimated_diameter: EstimatedDiameter,
            val id: String,
            val is_potentially_hazardous_asteroid: Boolean,
            val is_sentry_object: Boolean,
            val links: Links,
            val name: String,
            val nasa_jpl_url: String,
            val neo_reference_id: String
        ) : Parcelable {
            @Parcelize
            internal data class CloseApproachData(
                val close_approach_date: String,
                val close_approach_date_full: String,
                val epoch_date_close_approach: Long,
                val miss_distance: MissDistance,
                val orbiting_body: String,
                val relative_velocity: RelativeVelocity
            ) : Parcelable {
                @Parcelize
                internal data class MissDistance(
                    val astronomical: String,
                    val kilometers: String,
                    val lunar: String,
                    val miles: String
                ) : Parcelable

                @Parcelize
                internal data class RelativeVelocity(
                    val kilometers_per_hour: String,
                    val kilometers_per_second: String,
                    val miles_per_hour: String
                ) : Parcelable
            }

            @Parcelize
            internal data class EstimatedDiameter(
                val feet: Feet,
                val kilometers: Kilometers,
                val meters: Meters,
                val miles: Miles
            ) : Parcelable {
                @Parcelize
                internal data class Feet(
                    val estimated_diameter_max: Double,
                    val estimated_diameter_min: Double
                ) : Parcelable

                @Parcelize
                internal data class Kilometers(
                    val estimated_diameter_max: Double,
                    val estimated_diameter_min: Double
                ) : Parcelable

                @Parcelize
                internal data class Meters(
                    val estimated_diameter_max: Double,
                    val estimated_diameter_min: Double
                ) : Parcelable

                @Parcelize
                internal data class Miles(
                    val estimated_diameter_max: Double,
                    val estimated_diameter_min: Double
                ) : Parcelable
            }

            @Parcelize
            internal data class Links(
                val self: String
            ) : Parcelable
        }
    }
}