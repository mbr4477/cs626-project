# Build Playlists from Songs & Key Terms
Use Hadoop and MapReduce to build a custom playlist based on related songs and key terms.

Data can be downloaded from https://zenodo.org/record/2594557#__sid=js0 and the paper can be found on [IEEE Xplore](https://ieeexplore.ieee.org/document/7395827).

**Team:** Matthew Russell, Abuchi Okeke, David Adeniji

## Running
1. Build or use the included release JAR from GitHub
2. Create a folder for the project on HDFS
3. Create a subfolder for the input data `/data`
4. Copy the playlist data CSV file downloaded and unzipped from the [source](https://zenodo.org/record/2594557#__sid=js0) into the `/data` folder. You can directly download the source data using:
```bash
$ wget https://zenodo.org/record/2594557/files/spotify_playlists.zip
$ unzip spotify_playlists.zip
$ hadoop fs -mkdir -p ~/hdfs/project/data
$ hadoop fs -put spotify_playlists.csv ~/hdfs/project/data
```
5. Run the JAR file as:
```bash
$ hadoop jar cs626-project-1.0-SNAPSHOT.jar ~/hdfs/project ARTIST_QUERY,TRACK_QUERY,PLAYLIST_QUERY MAX_SONGS COUNT_CUTOFF COUNT_SPREAD
```
where `COUNT_CUTOFF` is the cutoff for how many times a song should appear to be relevant, and `COUNT_SPREAD` is the "blurriness" of this cutoff point (cannot be zero).
6. Output will be in the project `/job2` folder