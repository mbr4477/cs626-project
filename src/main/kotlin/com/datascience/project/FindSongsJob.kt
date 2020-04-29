/**
 * Author: Matthew Russell
 *
 * A MapReduce job that searches for songs
 * in the previously found playlists and reduces
 * them to the top relevant matches
 */
package com.datascience.project

import org.apache.hadoop.conf.Configured
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.FloatWritable
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.util.Tool

class FindSongsJob : Configured(), Tool {
    enum class SONGS {
        COUNT
    }
    override fun run(args: Array<out String>): Int {
        val job = Job.getInstance(conf, "BuildPlaylist")
        job.setJarByClass(this::class.java)
        FileInputFormat.addInputPath(job, Path(args[0] + "/data"))
        FileOutputFormat.setOutputPath(job, Path(args[0] + "/job2"))
        val fs = FileSystem.get(conf)
        fs.delete(FileOutputFormat.getOutputPath(job), true)

        // read the playlists from the first job output
        val playlistPath = Path(args[0] + "/job1/part-r-00000")
        val playlistFile = fs.open(playlistPath)
        Playlists.names.addAll(playlistFile.bufferedReader().readLines())
        println(Playlists.names)
        playlistFile.close()
        job.mapperClass = SongRelevanceMapper::class.java
        job.reducerClass = SongReducer::class.java
        job.mapOutputKeyClass = Text::class.java
        job.mapOutputValueClass = FloatWritable::class.java
        job.outputKeyClass = Text::class.java
        job.outputValueClass = FloatWritable::class.java

        return if (job.waitForCompletion(true)) 0 else 1
    }
}