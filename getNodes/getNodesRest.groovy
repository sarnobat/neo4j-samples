import org.apache.commons.io.*;
import org.apache.commons.io.IOUtils;
import java.net.URI;
import java.net.URISyntaxException;

import org.json.*;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
final String nodeEntryPointUri = SERVER_ROOT_URI + "index/auto/node/?query=name:*";
// http://localhost:7474/db/data/db/data/index/auto/node/?query=name:*

WebResource resource = Client.create()
		.resource( nodeEntryPointUri );
ClientResponse response = resource
.accept(MediaType.APPLICATION_JSON)
.get( ClientResponse.class );

String str = IOUtils.toString(response.getEntityInputStream(), "UTF-8");
JSONArray a = new JSONArray(str);
for (int i = 0; i < a.length(); i++) {
	JSONObject o = a.getJSONObject(i);
	println(o.get("data").get("name"));
}

response.close();