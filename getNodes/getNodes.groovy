
import static org.neo4j.kernel.impl.util.FileUtils.deleteRecursively;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.IteratorUtil;

private static final String DB_PATH = "target/java-query-db";
String resultString;
String columnsString;
String nodeResult;
String rows = "";


void run() {
	clearDbPath();

	// START SNIPPET: addData
	GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
	Transaction tx = db.beginTx();
	try
	{
		Node myNode = db.createNode();
		myNode.setProperty( "name", "my node" );
		tx.success();
	}
	finally
	{
		tx.finish();
	}
	// END SNIPPET: addData

	// START SNIPPET: execute
	ExecutionEngine engine = new ExecutionEngine( db );
	ExecutionResult result = engine.execute( "start n=node(*) where n.name! = 'my node' return n, n.name" );
	// END SNIPPET: execute
	// START SNIPPET: columns
	List<String> columns = result.columns();
	// END SNIPPET: columns
	// START SNIPPET: items
	Iterator<Node> n_column = result.columnAs( "n" );
	for ( Node node : IteratorUtil.asIterable( n_column ) )
	{
		// note: we're grabbing the name property from the node,
		// not from the n.name in this case.
		nodeResult = node + ": " + node.getProperty( "name" );
	}
	// END SNIPPET: items
	// the result is now empty, get a new one
	result = engine.execute( "start n=node(*) where n.name! = 'my node' return n, n.name" );
	// START SNIPPET: rows
	for ( Map<String, Object> row : result )
	{
		for ( Entry<String, Object> column : row.entrySet() )
		{
			rows += column.getKey() + ": " + column.getValue() + "; ";
		}
		rows += "\n";
	}
	// END SNIPPET: rows
	resultString = engine.execute( "start n=node(*) where n.name! = 'my node' return n, n.name" ).dumpToString();
	columnsString = columns.toString();
	db.shutdown();
}

private void clearDbPath()
{
	try
	{
		deleteRecursively( new File( DB_PATH ) );
	}
	catch ( IOException e )
	{
		throw new RuntimeException( e );
	}
}

JavaQuery javaQuery = new JavaQuery();
javaQuery.run();
