package com.example.android.material_design;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class Movie  {

    private int id;
    private long dbId;
    public String title;
    private String releaseDateTheater;
    private int audienceScore;
    private String overview;
    private String urlThumbnail;
    private String urlSelf;
    private String urlCast;
    private String urlReviews;
    private String urlSimilar;
    private String tagLine;
    private String status;
    private String genre;
    private String image;
    private String coverImage;
    int duration;
    float popularity;
    String stringid;
    float rating;
    String language;


    public Movie() {

    }
public Movie(int id)
{
    this.id=id;
}
    public Movie(int id,String title,String overview,String releaseDateTheater,String urlSelf)
    {
        this.id=id;
        this.title=title;
        this.overview=overview;
        this.releaseDateTheater=releaseDateTheater;
        this.urlSelf=urlSelf;
    }


    public Movie(int id,
                 String title,
                 String releaseDateTheater,
                 int audienceScore,
                 String overview,
                 String urlThumbnail,
                 String urlSelf,
                 String urlCast,
                 String urlReviews,
                 String urlSimilar) {
        this.id = id;
        this.title = title;
        this.releaseDateTheater = releaseDateTheater;
        this.audienceScore = audienceScore;
        this.overview = overview;
        this.urlThumbnail = urlThumbnail;
        this.urlSelf = urlSelf;
        this.urlCast = urlCast;
        this.urlReviews = urlReviews;
        this.urlSimilar = urlSimilar;
    }

    public int getId() {
       return id;
   }
//
   public void setId(int id) {
       this.id = id;
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

  //  public int getAudienceScore() {
   //     return audienceScore;
   // }

   // public void setAudienceScore(int audienceScore) {
   //     this.audienceScore = audienceScore;
   // }

   // public String getSynopsis() {
  //      return synopsis;
   // }

   // public void setSynopsis(String synopsis) {
   //     this.synopsis = synopsis;
   //

    public long getDbId() {
        return dbId;
    }

    public void setDbId(long id) {
        this.dbId = id;
    }
    public String getUrlThumbnail() {
    return urlThumbnail;
}

   public void setUrlThumbnail(String urlThumbnail) {
       this.urlThumbnail = urlThumbnail;
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

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
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
        this.image = image;
    }
    public String getImage(){
        return image;
    }

    public void setCoverImage(String coverImage){
        this.coverImage = coverImage;
    }
    public String getCoverImage(){
        return coverImage;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }
    public int getDuration(){
        return duration;
    }

    public void setPopularity(float popularity){
        this.popularity = popularity;
    }
    public float getPopularity(){
        return popularity;
    }



    public void setRating(float rating){
        this.rating = rating;
    }
    public float getRating(){
        return rating;
    }
    public void setStringid(String id){
        stringid=id;
    }
    public String getStringid(){
        return stringid;
    }

    public void setLanguage(String language){
        this.language = language;
    }
    public String getLanguage(){
        return language;
    }


    // public String getUrlCast() {
       // return urlCast;
    //}
//
 //   public void setUrlCast(String urlCast) {
  //      this.urlCast = urlCast;
  //  }

    public String getUrlReviews() {
       return urlReviews;
    }

    public void setUrlReviews(String urlReviews) {
      this.urlReviews = urlReviews;
    }

 //   public String getUrlSimilar() {
//        return urlSimilar;
 //   }

 ///   public void setUrlSimilar(String urlSimilar) {
  //      this.urlSimilar = urlSimilar;
 //   }

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nTitle " + title +
                "\nDate " + releaseDateTheater +
                "\nSynopsis " +overview +
                "\nScore " + audienceScore +
                "\nurlThumbnail " + urlThumbnail +
                "\nurlSelf " + urlSelf +
                "\nurlCast " + urlCast +
                "\nurlReviews " + urlReviews +
                "\nurlSimilar " + urlSimilar +
                "\n";
    }




}

