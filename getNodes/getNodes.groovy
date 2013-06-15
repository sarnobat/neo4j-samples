
import static org.neo4j.kernel.impl.util.FileUtils.deleteRecursively;
import org.neo4j.cypher.javacompat.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.IteratorUtil;


String resultString;
String columnsString;
String nodeResult;
String rows = "";


final String DB_PATH = "target/java-query-db";

// START SNIPPET: addData
//GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
EmbeddedGraphDatabase db = new EmbeddedGraphDatabase("/Users/sarnobat/neo4j-community-1.8.2/data/graph.db");
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
ExecutionResult result = engine.execute( " START n=node(*) RETURN n" );
// END SNIPPET: execute
// START SNIPPET: columns
List<String> columns = result.columns();
Iterator<Node> n_column = result.columnAs( "n" );
for ( Node node : IteratorUtil.asIterable( n_column ) )
{
	// note: we're grabbing the name property from the node,
	// not from the n.name in this case.
	println(node.getPropertyValues());

}
// END SNIPPET: items

resultString = engine.execute( "start n=node(*) where n.name! = 'my node' return n, n.name" ).dumpToString();
columnsString = columns.toString();
db.shutdown();
