package org.myorg;

import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SpotifyMapper1 extends Configured implements Tool {

    public static  String userArtistName;
    public static  String userTrackName;
    public static  String userKeyWord;

  private static final Logger LOG = Logger.getLogger(SpotifyMapper1.class);

  public static void main(String[] args) throws Exception {
    int res = ToolRunner.run(new SpotifyMapper1(), args);
    System.exit(res);
  }

  public int run(String[] args) throws Exception {
    
    Configuration conf = this.getConf();
 
    // Create job
    Job job = new Job(conf, "spotifymapper1");
    job.setJarByClass(SpotifyMapper1.class);
    // Use TextInputFormat, the default unless job.setInputFormatClass is used
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    job.setMapperClass(Map.class);
   // job.setReducerClass(Reduce.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    return job.waitForCompletion(true) ? 0 : 1;
  }

    public static class Map extends Mapper<LongWritable, Text, Text, Text> {
    private long numRecords = 0;    
    private static final Pattern WORD_BOUNDARY = Pattern.compile(",");  
  
    private final static Text word = new Text();

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

	 String artistname = line[1];
	 String trackname = line[2];
	 String playlistname = line[3];
	
      //sort songs by user inputs
        Configuration config = context.getConfiguration();
        String inputString = config.get("userinput");
        String[] userInput = inputString.split(",");
        userArtistName = userInput[0];
        userTrackName = userInput[1];
        userKeyWord = userInput[2];

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

  //public static class Reduce extends Reducer<Text,Text, Text, Text> {
   // @Override
   // public void reduce(Text artistnames, Iterable<Text> tracknames, Context context)
     //   throws IOException, InterruptedException {

      // for(Text song:tracknames){
 
    //         context.write(artistnames,song);
   // }
  //}
//}
}
