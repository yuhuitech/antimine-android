package dev.lucasnlm.external.model

import java.lang.Exception

data class CloudSave(
    val playId: String,
    val completeTutorial: Int,
    val selectedTheme: Int,
    val squareRadius: Int,
    val squareSize: Int,
    val touchTiming: Int,
    val questionMark: Int,
    val gameAssistance: Int,
    val help: Int,
    val hapticFeedback: Int,
    val soundEffects: Int,
    val stats: List<HashMap<String, String>>,
    val premiumFeatures: Int,
    val controlStyle: Int,
    val noGuessing: Int,
    val language: String,
    val openDirectly: Int,
    val unlockedThemes: String,
    val squareDivider: Int,
    val doubleClickTimeout: Int,
)

fun CloudSave.toHashMap(): HashMap<String, Any> = hashMapOf(
    "uid" to playId,
    "completeTutorial" to completeTutorial,
    "selectedTheme" to selectedTheme,
    "squareRadius" to squareRadius,
    "squareSize" to squareSize,
    "touchTiming" to touchTiming,
    "questionMark" to questionMark,
    "gameAssistance" to gameAssistance,
    "help" to help,
    "hapticFeedback" to hapticFeedback,
    "soundEffects" to soundEffects,
    "stats" to stats,
    "premiumFeatures" to premiumFeatures,
    "controlStyle" to controlStyle,
    "noGuessing" to noGuessing,
    "language" to language,
    "openDirectly" to openDirectly,
    "unlockedThemes" to unlockedThemes,
    "squareDivider" to squareDivider,
    "doubleClickTimeout" to doubleClickTimeout,
)

private fun Any?.parseInt(): Int = this?.toString()?.toInt() ?: throw Exception("Fail to parse Int")
private fun Any?.parseInt(default: Int): Int = this?.toString()?.toInt() ?: default
private fun Any?.parseString(default: String): String = this?.toString() ?: default

@Suppress("UNCHECKED_CAST")
fun cloudSaveOf(id: String, data: Map<String, Any>) =
    CloudSave(
        playId = id,
        completeTutorial = data["completeTutorial"].parseInt(),
        selectedTheme = data["selectedTheme"].parseInt(),
        squareRadius = data["squareRadius"].parseInt(),
        squareSize = data["squareSize"].parseInt(),
        touchTiming = data["touchTiming"].parseInt(),
        questionMark = data["questionMark"].parseInt(),
        gameAssistance = data["gameAssistance"].parseInt(),
        help = data["help"].parseInt(),
        hapticFeedback = data["hapticFeedback"].parseInt(),
        soundEffects = data["soundEffects"].parseInt(),
        stats = data["stats"] as List<HashMap<String, String>>,
        premiumFeatures = data["premiumFeatures"].parseInt(),
        controlStyle = data["controlStyle"].parseInt(),
        noGuessing = data["noGuessing"].parseInt(1),
        language = data["language"].parseString(""),
        openDirectly = data["openDirectly"].parseInt(0),
        unlockedThemes = data["unlockedThemes"].parseString(""),
        squareDivider = data["squareDivider"].parseInt(10),
        doubleClickTimeout = data["doubleClickTimeout"].parseInt(400),
    )
