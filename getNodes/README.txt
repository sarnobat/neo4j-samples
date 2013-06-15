It seems there are 2 properties, one being a duplicate of the other:

node_autoindexing_enabled=true # Commented out in neo4j.properties - but has no effect
node_auto_indexing=true        # Not in the file at all, but when added did index new nodes
node_keys_indexable=name,_id
It seems the 2nd one enables indexing but the 1st one does not. Hopefully if this is a bug it will get addressed (or has been addressed since the version I downloaded).

