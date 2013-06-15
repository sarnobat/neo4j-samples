
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



void addProperty( String nodeUri, String propertyName,
		String propertyValue )
{
	// START SNIPPET: addProp
	String propertyUri = nodeUri + "/properties/" + propertyName;
	// http://localhost:7474/db/data/node/{node_id}/properties/{property_name}

	WebResource resource = Client.create()
			.resource( propertyUri );
	ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )
			.type( MediaType.APPLICATION_JSON )
			.entity( "\"" + propertyValue + "\"" )
			.put( ClientResponse.class );

	System.out.println( String.format( "PUT to [%s], status code [%d]",
			propertyUri, response.getStatus() ) );
	response.close();
	// END SNIPPET: addProp
}

final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
final String nodeEntryPointUri = SERVER_ROOT_URI + "node/1";
// http://localhost:7474/db/data/node
 
addProperty(nodeEntryPointUri, "lastName", "Sarnobat");