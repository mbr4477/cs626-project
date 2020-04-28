/**
 * Author: Matthew Russell
 * A reducer that reduces the playlist names to
 * names and counts
 */
package com.datascience.project

import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer

class PlaylistReducer: Reducer<Text, NullWritable, Text, IntWritable>() {
    /**
     * key: row of input data file
     * values: null (number of values = number of times the row
     *         was considered a relevant match)
     */
    override fun reduce(key: Text, values: MutableIterable<NullWritable>, context: Context) {
        // simply pass along the playlist names and counts
        var count = 0
        for (value in values) {
            count++
        }
        context.write(key, IntWritable(count))
    }
}
