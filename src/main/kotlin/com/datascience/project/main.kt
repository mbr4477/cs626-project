/**
 * Author: Matthew Russell
 */
package com.datascience.project

import org.apache.hadoop.util.ToolRunner
import kotlin.system.exitProcess

class UserInput {
    companion object {
        @JvmField
        var artist: String = ""
        @JvmField
        var track: String = ""
        @JvmField
        var keyword: String = ""
        var maxSongs: Int = 50
        var countCutoff: Float = 100f
        var countSpread: Float = 10f
    }
}

class Playlists {
    companion object {
        val names = mutableListOf<String>()
    }
}

fun main(args: Array<String>) {
    ToolRunner.run(FindPlaylistsJob(), args)
    val resSongs = ToolRunner.run(FindSongsJob(), args)
    exitProcess(resSongs)
}