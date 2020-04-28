/**
 * Author: Matthew Russell
 *
 * A MapReduce job that searches for playlists relevant
 * to the user's query
 */
package com.datascience.project

import org.apache.hadoop.conf.Configured
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.util.Tool

class UserInput {
    companion object {
        var artist: String = ""
        var track: String = ""
        var keyword: String = ""
    }
}

class FindPlaylistsJob : Configured(), Tool {
    // boilerplate code for creating a map reduce job
    override fun run(args: Array<out String>): Int {
        val job = Job.getInstance(conf, "BuildPlaylist")
        job.setJarByClass(this::class.java)
        FileInputFormat.addInputPath(job, Path(args[0] + "/data"))
        FileOutputFormat.setOutputPath(job, Path(args[0] + "/job1"))
        val fs = FileSystem.get(conf)
        fs.delete(FileOutputFormat.getOutputPath(job), true)
        val input = args[1].toLowerCase().split(',')
        UserInput.artist = input[0]
        UserInput.track = input[1]
        UserInput.keyword = input[2]

//        job.mapperClass = TODO("Needs implemented")
        job.reducerClass = PlaylistReducer::class.java
        job.mapOutputKeyClass = Text::class.java
        job.mapOutputValueClass = NullWritable::class.java
        job.outputKeyClass = Text::class.java
        job.outputValueClass = IntWritable::class.java

        return if (job.waitForCompletion(true)) 0 else 1
    }
}