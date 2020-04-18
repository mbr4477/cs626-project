/**
 * Author: Matthew Russell
 */
package com.datascience.project

import org.apache.hadoop.util.ToolRunner
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val resPlaylist = ToolRunner.run(FindPlaylistsJob(), args)
    val resSongs = ToolRunner.run(FindSongsJob(), args)
    exitProcess(resSongs)
}