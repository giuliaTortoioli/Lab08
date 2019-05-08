package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	Graph<String, DefaultEdge> grafo;
	List<String> parole;
	List<String> vicini;

	public void createGraph(int numeroLettere) {
		
		this.grafo = new SimpleGraph<>(DefaultEdge.class);  
		
		parole = new ArrayList<>();
		
		WordDAO wdao = new WordDAO();
		parole.addAll(wdao.getAllWordsFixedLength(numeroLettere));
		
		Graphs.addAllVertices(this.grafo, this.parole);	

		System.out.println(this.grafo+" "+this.grafo.vertexSet().size());
		
		for(String p1 : parole) {
			for(String p2: parole) {
				if(paroleVicine(p1,p2))
					this.grafo.addEdge(p1, p2);
			}
		}
		
		System.out.println(this.grafo+" Vertici: "+this.grafo.vertexSet().size()+" Archi: "+this.grafo.edgeSet().size());
		
	}

	private boolean paroleVicine(String p1, String p2) {
		int conta = 0;
		for(int i = 0 ; i<p1.length() ; i++) {
			if(p1.charAt(i)!=p2.charAt(i))
				conta++;	
		}
		if(conta==1)
		return true;
		else
			return false;
		
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		vicini = new ArrayList<>();
		this.vicini = Graphs.neighborListOf(this.grafo, parolaInserita);
		
		return vicini;
	}

	public int findMaxDegree() {
		vicini = new ArrayList<>();
		int gradoMax = 0;
		for(String p : parole) {
			if(this.grafo.degreeOf(p)>gradoMax) {
				vicini = displayNeighbours(p);
				gradoMax = this.grafo.degreeOf(p);
			}
		}
		return gradoMax;
	}
	
	public List<String> getVicini() {
		return vicini;
	}

	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

	public List<String> getParole() {
		return parole;
	}
	
	
}
