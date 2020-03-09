package com.datascience.project

import org.apache.hadoop.util.ToolRunner
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val res = ToolRunner.run(BuildPlaylistJob(), args)
    exitProcess(res)
}