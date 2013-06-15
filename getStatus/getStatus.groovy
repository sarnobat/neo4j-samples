
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
final String nodeEntryPointUri = SERVER_ROOT_URI + "node";
// http://localhost:7474/db/data/node
 
WebResource resource = Client.create()
        .resource( SERVER_ROOT_URI );
ClientResponse response = resource.get( ClientResponse.class );
 
System.out.println( String.format( "GET on [%s], status code [%d]",
        SERVER_ROOT_URI, response.getStatus() ) );
response.close();