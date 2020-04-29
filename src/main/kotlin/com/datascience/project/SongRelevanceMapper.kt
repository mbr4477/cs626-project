/**
 * Author: Matthew Russell
 */
package com.datascience.project

import org.apache.hadoop.io.FloatWritable
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper

class SongRelevanceMapper: Mapper<LongWritable, Text, Text, FloatWritable>() {
    /**
     * inputs: line with playlist name, artist name, and song name
     * outputs: song and artist name, relevance score
     */
    override fun map(key: LongWritable, line: Text, context: Context) {
        val lineStr = line.toString()
        val parts = lineStr.split("\",\"")
        try {
            val playlist = parts[3].toLowerCase().trim('"')
            val artist = parts[1].toLowerCase().trim('"')
            val track = parts[2].toLowerCase().trim('"')
            if (Playlists.names.contains(playlist) && track.isNotEmpty() && artist.isNotEmpty()) {
                val artistMatches = UserInput.artist.contains(artist) || artist.contains(UserInput.artist)
                val trackMatches = UserInput.track.contains(track) || track.contains(UserInput.track)
                var relevance = 0.5f
                if (artistMatches) {
                    relevance += 0.25f
                }
                if (trackMatches) {
                    relevance += 0.25f
                }
                context.write(Text("\"$artist\",\"$track\""), FloatWritable(relevance))
            }
        } catch (e: Exception) {
            // skip if an error
        }

    }
}