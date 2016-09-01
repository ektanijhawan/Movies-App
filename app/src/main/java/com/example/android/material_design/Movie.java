package com.example.android.material_design;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class Movie  {
    private int dbId;
    private String id;
    String title;
    private String urlSelf;
    private String coverImage;
    String audienceScore;
    String popularity;
    private String tagLine;
    private String releaseDateTheater;
    String duration;
    private String genre;
    private String overview;







    public Movie() {

    }

    public Movie(String id,String title,String urlSelf,String coverImage,String audienceScore,String popularity,String tagLine,String releaseDate,String duration,String genre,String overview)
    {
        this.id=id;
        this.title=title;
        this.urlSelf=urlSelf;
        this.coverImage=coverImage;
        this.tagLine=tagLine;
        this.audienceScore=audienceScore;
        this.popularity=popularity;
        this.tagLine=tagLine;
        this.releaseDateTheater=releaseDate;
        this.duration=duration;
        this.genre=genre;
        this.overview=overview;


    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDateTheater() {
        return releaseDateTheater;
   }
  public void setReleaseDateTheater(String releaseDateTheater){this.releaseDateTheater=releaseDateTheater;}
    public void setOverview(String overview){this.overview=overview;}

    public String getOverview(){  return overview;}

    public String getAudienceScore() {
        return audienceScore;
    }

    public void setAudienceScore(String audienceScore) {
       this.audienceScore = audienceScore;
    }






    public String getUrlSelf() {
       return urlSelf;
   }

    public void setUrlSelf(String urlSelf) {
        this.urlSelf = urlSelf;
    }
    public void setTagLine(String tagLine){
        this.tagLine = tagLine;
    }
    public String getTagLine(){
        return tagLine;
    }


    public void setReleasedate(String releaseDate){
        this.releaseDateTheater = releaseDate;
    }
    public String getReleasedate(){
        return releaseDateTheater;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getGenre(){
        return genre;
    }

    public void setImage(String image){
        this.urlSelf = urlSelf;
    }
    public String getImage(){
        return urlSelf;
    }

    public void setCoverImage(String coverImage){
        this.coverImage = coverImage;
    }
    public String getCoverImage(){
        return coverImage;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }
    public String getDuration(){
        return duration;
    }

    public void setPopularity(String popularity){
        this.popularity = popularity;
    }
    public String getPopularity(){
        return popularity;
    }




    public void setStringid(String id){
        this.id=id;
    }
    public String getStringid(){
        return id;
    }



    @Override
    public String toString() {
        return "\nID: " + id +
                "\nTitle " + title +
                "\nDate " + releaseDateTheater +
                "\nSynopsis " +overview +
                "\nScore " + audienceScore +


                "\n";
    }




}

