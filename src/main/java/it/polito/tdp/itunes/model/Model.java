package it.polito.tdp.itunes.model;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	ItunesDAO dao;
	Graph<Album, DefaultEdge> graph;
	
	
	
	
	
	public Model() {
		this.dao = new ItunesDAO();
		this.graph = new SimpleGraph<>(DefaultEdge.class);
	}





	public Graph creaGrafo(int durata) {
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		
		Graphs.addAllVertices(this.graph, this.dao.getVertices(durata));
		
		for(Album a1: this.graph.vertexSet()) {
			for(Album a2 : this.graph.vertexSet()) {
				if(!a1.equals(a2)) {
					
					List<TrackPlaylist> listaA1 = this.dao.getListaPerAlbum(a1.getAlbumId());
					List<TrackPlaylist> listaA2 = this.dao.getListaPerAlbum(a2.getAlbumId());
					
					for(TrackPlaylist tp1 : listaA1) {
						for(TrackPlaylist tp2 : listaA2) {
							if(tp1.getPlaylistId() == tp2.getPlaylistId()) {
								Graphs.addEdgeWithVertices(this.graph, a1, a2);
							}
						}
					}
				}
			}
		}
		
		
		return this.graph;
	}
	
	public Integer calcolaConnessa(Album a) {
		
		
		// Modo 2: uso la classe ConnectivityInspector che già implementa un metodo per 
		// recuperare tutti i nodi connesso ad un nodo radice
		
		ConnectivityInspector<Album, DefaultEdge> inspector = new ConnectivityInspector<>(this.graph);
		Set<Album> setConnesso = inspector.connectedSetOf(a);
		
		return setConnesso.size();
	}
	
	public Integer calcolaTrack(Album a) {
		ConnectivityInspector<Album, DefaultEdge> inspector = new ConnectivityInspector<>(this.graph);
		Set<Album> setConnesso = inspector.connectedSetOf(a);
		
		int restituire = 0;
		
		for(Album al : setConnesso) {
			restituire += this.dao.getListaTrack(al.getAlbumId()).size();
		}
		
		return restituire;
	}
	
	
}
