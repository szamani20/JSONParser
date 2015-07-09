# JSONParser
Apple itunes JSON parser.
This app is an example of parsing JSON data. First it downloads JSON from http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/json
and then parses it using json-java.jar and stores it in a java class.
Basically keys used in argument of JSONObject() and JSONArray() depend on the data in json.
So in this case the array name is "entry".
