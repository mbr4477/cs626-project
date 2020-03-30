package com.datascience.project

import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer

class PlaylistReducer: Reducer<Text, NullWritable, Text, NullWritable>() {
    /*
     * key: playlist name
     * values: null (number of values = number of times that playlist
     *         was considered a relevant match
     */
    override fun reduce(key: Text, values: MutableIterable<NullWritable>, context: Context) {
        // simply pass along the playlist names
        context.write(key, NullWritable.get())
    }
}
