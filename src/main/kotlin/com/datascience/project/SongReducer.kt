/**
 * Author: Matthew Russell
 * A reducer that reduces the matched songs
 * to the top most relevant
 */
package com.datascience.project

import org.apache.hadoop.io.FloatWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer
import java.util.*

class SongReducer: Reducer<Text, FloatWritable, Text, FloatWritable>() {
    /**
     * key: song name
     * values: relevance weight (0 - 1)
     */
    override fun reduce(key: Text, values: MutableIterable<FloatWritable>, context: Context) {
        // randomly pick this song based on relevance
        // use the max relevance if this song appears
        // more than once
        var maxRelevance = 0.0f
        for (r in values) {
            if (r.get() > maxRelevance) {
                maxRelevance = r.get()
            }
        }
        if (Random().nextDouble() < maxRelevance) {
            context.write(key, FloatWritable(maxRelevance))
        }
    }
}
