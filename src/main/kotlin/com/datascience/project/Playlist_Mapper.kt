package com.datascience.project

import org.apache.hadoop.conf.Configured
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.Mapper
import org.apache.hadoop.mapreduce.Reducer
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat
import org.apache.hadoop.util.Tool

class Playlist_Mapper: Mapper<LongWritable, Text, NullWritable, Text>() {
    override fun map(offset: LongWritable, line: Text, context: Context) {
        val lineStr = line.toString()
        val sortedLine = lineStr
            .split(',')



        // write the sorted pair
        // this line writes to the map reduce context to provide
        // the intermediate outputs for the reducer
        // output the sorted key and 1
        context.write(NullWritable.get(), Text(sortedLine[2]))
    }
}