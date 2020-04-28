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
        val parts = lineStr.split(',')
        val artist = parts[0].toLowerCase()
        val track = parts[1].toLowerCase()
        val playlist = parts[2].toLowerCase()
        val artistMatches = UserInput.artist.contains(artist) || artist.contains(UserInput.artist)
        val playlistMatches = UserInput.keyword.contains(playlist) || playlist.contains(UserInput.keyword)
        val trackMatches = UserInput.track.contains(track) || artist.contains(UserInput.track)
        var relevance = 0.0f
        if (artistMatches) {
            relevance += 1 / 3f
        }
        if (playlistMatches) {
            relevance += 1 / 3f
        }
        if (trackMatches) {
            relevance += 1 / 3f
        }
        context.write(Text("$artist,$track"), FloatWritable(relevance))
    }
}