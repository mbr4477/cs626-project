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
import kotlin.math.exp

class SongReducer: Reducer<Text, FloatWritable, Text, Text>() {
    /**
     * key: song name
     * values: relevance weight (0 - 1)
     */
    override fun reduce(key: Text, values: MutableIterable<FloatWritable>, context: Context) {
        // randomly pick this song based on relevance
        // use the max relevance if this song appears
        // more than once
        val counter = context.getCounter(FindSongsJob.SONGS.COUNT)
        if (counter.value < UserInput.maxSongs) {
            var maxRelevance = 0.0f
            var count = 0
            for (r in values) {
                if (r.get() > maxRelevance) {
                    maxRelevance = r.get()
                }
                count += 1
            }
            // re-weight based on number of occurrences
            val spread = UserInput.countSpread
            val cutoff = UserInput.countCutoff
            // argument for sigmoid weighting function
            val expArg = (count - cutoff) / spread
            maxRelevance *= exp(expArg) / (1f + exp(expArg))
            if (Random().nextDouble() < maxRelevance) {
                context.write(key, Text("weight=%.5f,count=%d".format(maxRelevance, count)))
                counter.increment(1)
            }
        }
    }
}
