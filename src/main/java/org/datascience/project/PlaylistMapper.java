/**
 * Author: Abuchi Okeke
 * Date: 04/28/2020
 * Mapper class for mapReduce algorithm to find matching 
 * songs using items from user inputs
 */

package org.datascience.project;

import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

//import com.datascience.project.FindPlaylistsJob;  

import org.apache.log4j.Logger;

    public class PlaylistMapper extends Mapper<LongWritable, Text, Text, Text> {
     
   /*Correct the name of the class "FindPlaylistsJobKt" for the kotlin byte code
    *Or call direct using @JvmStatic or @JvmField (add to the companion object and call class direct)
    *Not sure if it's going to work */

    String userArtistName = FindPlaylistsJobKt.UserInput.artist;
    String userTrackName = FindPlaylistsJobKt.UserInput.track;
    String userKeyWord = FindPlaylistsJobKt.UserInput.keyword;
  
    private static final Pattern WORD_BOUNDARY = Pattern.compile(",");  
 
    //Method to find matching songs
    boolean checkUserInputExistence(String userinput, String spotifyinput) {
    String input = userinput.toLowerCase();
    String spotifyItem = spotifyinput.toLowerCase();

    if (spotifyItem.matches(".*" + input + ".*")) {
    return true;
    }
    return false;
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
        
          if (checkUserInputExistence(userArtistName, artistname) || checkUserInputExistence(userTrackName, trackname)){
              context.write(new Text(artistname), new Text (trackname));
        }

         if ( checkUserInputExistence(userKeyWord, playlistname)){
             context.write(new Text(artistname), new Text (trackname));
       }
    }
  }