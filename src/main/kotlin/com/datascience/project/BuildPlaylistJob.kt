package com.datascience.project

import org.apache.hadoop.conf.Configured
import org.apache.hadoop.fs.Path
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.util.Tool

class BuildPlaylistJob : Configured(), Tool {
    // boilerplate code for creating a map reduce job
    override fun run(args: Array<out String>): Int {
        val job = Job.getInstance(conf, "BuildPlaylist")
        job.setJarByClass(this::class.java)
        FileInputFormat.addInputPath(job, Path(args[0]))
        FileOutputFormat.setOutputPath(job, Path(args[1]))

//        job.mapperClass = TODO("Needs implemented")
//        job.reducerClass = TODO("Needs implemented")
//        job.outputKeyClass = TODO("Needs to be selected")
//        job.outputValueClass = TODO("Needs to be selected")

        return if (job.waitForCompletion(true)) 0 else 1
    }
}