/**
 * Author: Matthew Russell
 * A reducer that reduces the playlist names to
 * names and counts
 */
package com.datascience.project

import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer

class PlaylistReducer: Reducer<Text, Text, Text, NullWritable>() {
    /**
     * key: playlist name
     * values: null
     */
    override fun reduce(key: Text, values: MutableIterable<Text>, context: Context) {
        context.write(Text(key.toString().toLowerCase().trim('"')), NullWritable.get())
    }
}
