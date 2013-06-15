This unfortunately will only work if the script is located on the same machine as the DB, which makes it impossible to scale the app servers. We have to stick to REST.

The advantage of this approach though, is that you don't need a neo4j-server process running. It accesses the data store directly.
