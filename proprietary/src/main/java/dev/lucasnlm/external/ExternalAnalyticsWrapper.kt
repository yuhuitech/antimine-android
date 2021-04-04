package dev.lucasnlm.external

import android.content.Context
import android.os.Bundle
import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import com.google.firebase.analytics.FirebaseAnalytics
import org.json.JSONObject

class ExternalAnalyticsWrapper(
    private val context: Context,
) : IExternalAnalyticsWrapper {
    private val firebaseAnalytics: FirebaseAnalytics? by lazy {
        try {
            FirebaseAnalytics.getInstance(context)
        } catch (e: Throwable) {
            // If fail to initialize Firebase, just ignore it.
            null
        }
    }
    private val amplitudeClient: AmplitudeClient? by lazy {
        try {
            Amplitude
                .getInstance()
                .enableCoppaControl()
                .initialize(context, "AMPLITUDE_API_KEY")
        } catch (e: Throwable) {
            // If fail to initialize Amplitude, just ignore it.
            null
        }
    }

    override fun setup(context: Context, properties: Map<String, String>) {
        properties.forEach { (key, value) ->
            firebaseAnalytics?.setUserProperty(key, value)
        }

        amplitudeClient?.setUserProperties(JSONObject(properties))
    }

    override fun sendEvent(name: String, content: Map<String, String>) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.ITEM_NAME, name)
            content.forEach { (key, value) ->
                this.putString(key, value)
            }
        }

        firebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
        amplitudeClient?.logEvent(name, JSONObject(content))
    }
}
