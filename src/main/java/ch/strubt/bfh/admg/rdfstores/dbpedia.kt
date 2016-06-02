package ch.strubt.bfh.admg.rdfstores

import org.apache.jena.query.QueryExecutionFactory
import org.apache.jena.query.QueryFactory
import org.apache.jena.query.ResultSetFormatter

fun main(vararg args: String) {

	for (queryString in Resources.queries) {
		val query = QueryFactory.create(queryString)

		QueryExecutionFactory.sparqlService(Resources.dbpediaService, query).use { execution ->
			val resultSet = execution.execSelect()
			ResultSetFormatter.out(System.out, resultSet, query)
		}
	}
}

object Resources {

	val dbpediaService = "http://dbpedia.org/sparql"

	val queries = listOf(Resources::class.java.getResourceAsStream("dbpedia-1.sparql").reader().readText(),
											 Resources::class.java.getResourceAsStream("dbpedia-2.sparql").reader().readText(),
											 Resources::class.java.getResourceAsStream("dbpedia-3.sparql").reader().readText())
}
