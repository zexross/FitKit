package com.example.fit_kit

import com.google.android.gms.fitness.FitnessActivities
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Session

fun String.fromDartType(): Type? {
    return when (this) {
        "heart_rate" -> Type.Sample(DataType.TYPE_HEART_RATE_BPM)
        "step_count" -> Type.Sample(DataType.TYPE_STEP_COUNT_DELTA)
        "height" -> Type.Sample(DataType.TYPE_HEIGHT)
        "weight" -> Type.Sample(DataType.TYPE_WEIGHT)
        "distance" -> Type.Sample(DataType.TYPE_DISTANCE_DELTA)
        "energy" -> Type.Sample(DataType.TYPE_CALORIES_EXPENDED)
        "water" -> Type.Sample(DataType.TYPE_HYDRATION)
        "sleep" -> Type.Activity(DataType.TYPE_SLEEP_SEGMENT)
        else -> null
    }
}

fun FitnessOptions.Builder.addDataTypes(dataTypes: List<DataType>) = apply {
    dataTypes.forEach { dataType -> addDataType(dataType, FitnessOptions.ACCESS_READ) }
}

// because this value is private field in Session
fun Session.getValue(): Int {
    return when (this.activity) {
        FitnessActivities.SLEEP -> 2
        FitnessActivities.SLEEP_LIGHT -> 4
        FitnessActivities.SLEEP_DEEP -> 5
        FitnessActivities.SLEEP_REM -> 6
        FitnessActivities.SLEEP_AWAKE -> 1
        else -> throw Exception("session ${this.activity} is not supported")
    }
}

class UnsupportedException(message: String) : Exception(message)