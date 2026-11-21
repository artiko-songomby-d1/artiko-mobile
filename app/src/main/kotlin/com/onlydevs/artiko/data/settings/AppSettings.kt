package com.onlydevs.artiko.data.settings

enum class ThemeMode { System, Light, Dark }

/** Text size steps; each scales every text of the app. */
enum class TextSize(val scale: Float) {
    Small(0.9f),
    Normal(1f),
    Large(1.15f),
    ExtraLarge(1.3f),
}

data class AppSettings(
    val theme: ThemeMode = ThemeMode.System,
    val textSize: TextSize = TextSize.Normal,
    /** Stops the movement effects: depth tilt, press feedback and screen transitions. */
    val reduceMotion: Boolean = false,
    /** "2D instead of 3D": no depth tilt, no artwork turning. */
    val flatMode: Boolean = false,
    val haptics: Boolean = true,
    /** Loads smaller images to save mobile data. */
    val dataSaver: Boolean = false,
) {
    /** Depth effects need motion and the 3D look. */
    val depthEffects: Boolean get() = !reduceMotion && !flatMode
}
