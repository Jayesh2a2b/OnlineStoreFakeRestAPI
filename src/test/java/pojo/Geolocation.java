package pojo;
/*
 *     geolocation:{
        lat:String,
        long:String
        }

 */
public class Geolocation {
private String lat;
private String longi;
public Geolocation(String lat, String longi) {
	this.lat = lat;
	this.longi = longi;
}
public String getLat() {
	return lat;
}
public void setLat(String lat) {
	this.lat = lat;
}
public String getLongi() {
	return longi;
}
public void setLongi(String longi) {
	this.longi = longi;
}

}
