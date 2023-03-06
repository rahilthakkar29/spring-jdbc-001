### What's the difference between RowMapper and ResultSetExtractor

- RowMapper will only single object at a time and map it to the object.
- On the other hand ResultSetExtractor will fetch all the result set at once so the method is called only one time and 
also can return the List as well.