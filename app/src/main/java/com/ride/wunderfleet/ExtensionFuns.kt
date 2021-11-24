package com.ride.wunderfleet

import android.content.res.AssetManager

object  ExtensionFuns {

    fun AssetManager.readAssetsFile(fileName : String): String = open(fileName).bufferedReader().use{it.readText()}

}
