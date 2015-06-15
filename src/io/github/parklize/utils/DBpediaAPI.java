package io.github.parklize.utils;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.sparql.engine.http.QueryExceptionHTTP;

// TODO: Auto-generated Javadoc
/**
 * The Class DBpediaAPI.
 */
public class DBpediaAPI {
	
	/** The Constant service. */
	public static final String service = "http://dbpedia.org/sparql";
    
    /**
     * Service test.
     */
    public static void serviceTest() {
	    String query = "ASK { }";
	    QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
	    try {
	        if (qe.execAsk()) {
	            System.out.println(service + " is UP");
	        } // end if
	    } catch (QueryExceptionHTTP e) {
	        System.out.println(service + " is DOWN");
	    } finally {
	        qe.close();
	    } // end try/catch/finally
    }
    
    /**
     * Gets links for Cii value of r_a r_b.
     * @param r_a the r_a
     * @param r_b the r_b
     * @return links for Cii value of r_a r_b
     */
    public static List<String> getCiiLinks(String r_a, String r_b) {
    	
    	List<String> link_list = new ArrayList<String>();
    	
    	String query_str = "SELECT DISTINCT ?p WHERE "
	    				  + "{?s ?p " + r_a + " . "
	    	              + "?s ?p " + r_b + " . "
//	    	              + "FILTER(STRSTARTS(STR(?p), 'http://dbpedia.org/ontology/')) "
	    	              + "}";
    	
    	Query query = QueryFactory.create(query_str);
    	QueryExecution qexec = QueryExecutionFactory.sparqlService(service, query);
	    ResultSet results = qexec.execSelect() ;
	    while(results.hasNext())
	    {
	      QuerySolution soln = results.nextSolution() ;
	      RDFNode property = soln.get("p") ;       // Get a result variable by name.
	      link_list.add(property.toString());
	    }
	    
	    return link_list;
    }
    
    /**
     * Gets links for Cio of r_a r_b.
     * @param r_a the r_a
     * @param r_b the r_b
     * @return links for Cio value of r_a r_b
     */
    public static List<String> getCioLinks(String r_a, String r_b) {
    	
    	List<String> link_list = new ArrayList<String>();
    	
    	String query_str = "SELECT DISTINCT ?p WHERE "
	    				  + "{" + r_a + " ?p ?o  ."
	    	              + r_b + " ?p ?o  ."
//	    	              + "FILTER(STRSTARTS(STR(?p), 'http://dbpedia.org/ontology/')) "
	    	              + "}";
    	
    	Query query = QueryFactory.create(query_str);
    	QueryExecution qexec = QueryExecutionFactory.sparqlService(service, query);
	    ResultSet results = qexec.execSelect() ;
	    while(results.hasNext())
	    {
	      QuerySolution soln = results.nextSolution() ;
	      RDFNode property = soln.get("p") ;       // Get a result variable by name.
	      link_list.add(property.toString());
	    }
	    
	    return link_list;
    }  
    
    /**
     * Gets the Cii value of r_a n.
     * @param r_a the r_a
     * @param property
     * @return the Cii value of r_a n
     */
    public static double getCiiRaN(String r_a, String property) {
    	
    	String query_str = "SELECT (COUNT(*) AS ?count) WHERE "
	    				  + "{?s " + property + " " + r_a + " . "
	    	              + "?s  " + property + " ?o . "
//	    	              + "FILTER(STRSTARTS(STR(?p), 'http://dbpedia.org/ontology/')) "
	    	              + "}";
    	
    	Query query = QueryFactory.create(query_str);
    	QueryExecution qexec = QueryExecutionFactory.sparqlService(service, query);
	    ResultSet results = qexec.execSelect() ;
	    while(results.hasNext())
	    {
	      QuerySolution soln = results.nextSolution() ;
	      RDFNode s = soln.get("count") ;       // Get a result variable by name.
	      return s.asLiteral().getDouble();
	    }
	    
	    return 0.0; // return 0 if no query result
    }
    
    public static double getCiiRaN(String r_a, String property, String r_b) {
    	
    	String query_str = "SELECT (COUNT(*) AS ?count) WHERE "
	    				  + "{?s " + property + " " + r_a + " . "
	    	              + "?s  " + property + " " + r_b + " . "
//	    	              + "FILTER(STRSTARTS(STR(?p), 'http://dbpedia.org/ontology/')) "
	    	              + "}";
    	
    	Query query = QueryFactory.create(query_str);
    	QueryExecution qexec = QueryExecutionFactory.sparqlService(service, query);
	    ResultSet results = qexec.execSelect() ;
	    while(results.hasNext())
	    {
	      QuerySolution soln = results.nextSolution() ;
	      RDFNode s = soln.get("count") ;       // Get a result variable by name.
	      return s.asLiteral().getDouble();
	    }
	    
	    return 0.0; // return 0 if no query result
    }
    
    public static double getCioRaN(String r_a, String property) {
    	
    	String query_str = "SELECT (COUNT(*) AS ?count) WHERE "
	    				  + "{" + r_a + " " + property + " ?o . "
	    	              + "?s  " + property + " ?o . "
//	    	              + "FILTER(STRSTARTS(STR(?p), 'http://dbpedia.org/ontology/')) "
	    	              + "}";
    	
    	Query query = QueryFactory.create(query_str);
    	QueryExecution qexec = QueryExecutionFactory.sparqlService(service, query);
	    ResultSet results = qexec.execSelect() ;
	    while(results.hasNext())
	    {
	      QuerySolution soln = results.nextSolution() ;
	      System.out.println(soln);
	      RDFNode s = soln.get("count") ;       // Get a result variable by name.
	      return s.asLiteral().getDouble();
	    }
	    
	    return 0.0; // return 0 if no query result
    }
    
    public static double getCioRaN(String r_a, String property, String r_b) {
    	
    	String query_str = "SELECT (COUNT(*) AS ?count) WHERE "
    					  + "{" + r_a + " " + property + " ?o . "
    					  + r_b + " " + property + " ?o . "
//	    	              + "FILTER(STRSTARTS(STR(?p), 'http://dbpedia.org/ontology/')) "
	    	              + "}";
    	
    	Query query = QueryFactory.create(query_str);
    	QueryExecution qexec = QueryExecutionFactory.sparqlService(service, query);
	    ResultSet results = qexec.execSelect() ;
	    while(results.hasNext())
	    {
	      QuerySolution soln = results.nextSolution() ;
	      RDFNode s = soln.get("count") ;       // Get a result variable by name.
	      return s.asLiteral().getDouble();
	    }
	    
	    return 0.0; // return 0 if no query result
    }
}
