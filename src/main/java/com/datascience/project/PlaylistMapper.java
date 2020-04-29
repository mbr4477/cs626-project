/**
 * Author: Abuchi Okeke
 * Date: 04/28/2020
 * Mapper class for mapReduce algorithm to find matching
 * songs using items from user inputs
 */

package com.datascience.project;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.regex.Pattern;

public class PlaylistMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
     
   /*Correct the name of the class "FindPlaylistsJobKt" for the kotlin byte code
    *Or call direct using @JvmStatic or @JvmField (add to the companion object and call class direct)
    *Not sure if it's going to work */

    String userArtistName = UserInput.artist;
    String userTrackName = UserInput.track;
    String userKeyWord = UserInput.keyword;
  
    private static final Pattern WORD_BOUNDARY = Pattern.compile(",");  
 
    //Method to find matching songs
    boolean checkUserInputExistence(String userinput, String spotifyinput) {
    String input = userinput.toLowerCase();
    String spotifyItem = spotifyinput.toLowerCase();

    return spotifyItem.matches(".*" + input + ".*");
}
    public void map(LongWritable offset, Text lineText, Context context)
        throws IOException, InterruptedException {

       //split data by comma ","
       String[] line = WORD_BOUNDARY.split(lineText.toString());
       //Get spotify items
   String artistname = line[1];
   String trackname = line[2];
   String playlistname = line[3];
  
      if (userKeyWord != null){
      //boolean isFound = playlistnames.indexOf(userKeyWord) !=-1? true: false;
      }
          // Matthew Russell: bug fix to write more relevant playlist names
          if (checkUserInputExistence(userArtistName, artistname) && checkUserInputExistence(userKeyWord, playlistname)
                  && (userTrackName.isEmpty() || checkUserInputExistence(userTrackName, trackname))){
              context.write(new Text(playlistname), NullWritable.get());
        }

    }
  }