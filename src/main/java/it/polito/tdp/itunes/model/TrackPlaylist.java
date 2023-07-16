package it.polito.tdp.itunes.model;

public class TrackPlaylist {

	Integer trackId;
	Integer playlistId;
	
	public TrackPlaylist(Integer trackId, Integer playlistId) {
		super();
		this.trackId = trackId;
		this.playlistId = playlistId;
	}

	public Integer getTrackId() {
		return trackId;
	}

	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}

	public Integer getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(Integer playlistId) {
		this.playlistId = playlistId;
	}
	
	
}
