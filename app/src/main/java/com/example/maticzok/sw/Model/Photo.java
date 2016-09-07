package com.example.maticzok.sw.Model;

/**
 * Created by Maticzok on 2016-07-10.
 */
public class Photo {
    /**
     * albumId : 3
     * id : 146
     * title : ullam dolor ut ipsa veniam
     * url : http://placehold.it/600/6c746e
     * thumbnailUrl : http://placehold.it/150/bb563a
     */

    private String albumId;
    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
