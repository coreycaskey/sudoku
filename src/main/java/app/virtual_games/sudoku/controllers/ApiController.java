package app.virtual_games.sudoku.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * 
 * Facilitates requests to the external sudoku API.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class ApiController 
{
	private static final String BASE_API_URL = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=9&level=%d";
	
	
	/**
	 *
	 * Retrieves a sudoku puzzle of the requested difficulty.
	 *
	 * @param difficulty 			 : unique identifier for the puzzle difficulty (e.g. Easy —> 1)
	 * 
	 * @return ArrayList<JSONObject> : list of initial cell JSON Objects
	 * 
	 */
	public static ArrayList<JSONObject> getSudokuPuzzle(int difficulty) throws Exception
	{		
		URL apiUrl = new URL(String.format(BASE_API_URL, difficulty));
		HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
		ArrayList<JSONObject> initialCells = getInitialCells(sendRequest(connection));

		if (connection != null) { connection.disconnect(); }
		
		return initialCells;
	}
	
	
	/**  Private Helper Methods  **/
	

	/**
	 * 
	 * Sends and processes the API request.
	 * 
	 * @throws Exception
	 * 
	 * @param connection : HTTP connection to the API
	 * 
	 * @return String	 : API response
	 * 
	 */
	private static String sendRequest(HttpURLConnection connection) throws Exception 
	{
		if (getResponseCode(connection) == HttpURLConnection.HTTP_OK) 
		{ 
			return loadApiResponse(connection);
		}
		
		throw new Exception("Failed Api Connection.");
	}
	
	
	/**
	 * 
	 * Retrieves the request response code.
	 * 
	 * @throws Exception
	 * 
	 * @param connection : HTTP connection to the API
	 * 
	 * @return int 		 : response code (e.g. Success —> 200)
	 * 
	 */
	private static int getResponseCode(HttpURLConnection connection) throws Exception 
	{
		if (connection == null) { throw new Exception("Failed Api Connection."); }
		
		connection.setRequestMethod("GET");
		connection.connect();
		
		return connection.getResponseCode();
	}
	
	
	/**
	 * 
	 * Loads the API response.
	 * 
	 * @throws Exception
	 * 
	 * @param connection : HTTP connection to the API
	 * 
	 * @return String	 : API response
	 * 
	 */
	private static String loadApiResponse(HttpURLConnection connection) throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String line;
		
		while ((line = reader.readLine()) != null) { response.append(line); }
		
		reader.close();
				
		return response.toString();
	}
	
	
	/**
	 * 
	 * Retrieves a row—ordered list of initial cell {@link JSONObject} elements.
	 * 
	 * @throws Exception
	 *
	 * @param response 	 			 : multi—line string of initial cells
	 * 
	 * @return ArrayList<JSONObject> : list of initial cell JSON Objects
	 * 
	 */	
	private static ArrayList<JSONObject> getInitialCells(String response) throws Exception  
	{
		return sortInitialCells(mapJsonArray(getJsonArray(response)));
	}
	
	
	/**
	 * 
	 * Retrieves a column—ordered list of initial cell {@link JSONObject} elements.
	 * 
	 * @throws Exception
	 * 
	 * @param response 	 			 : multi—line string of initial cells
	 * 
	 * @return ArrayList<JSONObject> : list of initial cell JSON Objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	private static ArrayList<JSONObject> getJsonArray(String response) throws Exception
	{
		return (JSONArray) ((JSONObject) new JSONParser().parse(response)).get("squares");
	}
	
	
	/**
	 * 
	 * Retrieves an updated list of initial cell {@link JSONObject} elements.
	 * 
	 * @param jsonArray  			 : list of initial cell JSON Objects
	 * 
	 * @return ArrayList<JSONObject> : list of initial cell JSON Objects
	 * 
	 */
	private static ArrayList<JSONObject> mapJsonArray(ArrayList<JSONObject> jsonArray)
	{
		return jsonArray.stream()
				   		.map(cellJson -> updateJson(cellJson))
				   		.collect(Collectors.toCollection(ArrayList::new));
	}
	
	
	/**
	 * 
	 * Updates the key—value pairs for a {@link JSONObject} element.
	 * 
	 * @param object 	  : initial cell Object
	 * 
	 * @return JSONObject : initial cell JSON Object
	 * 
	 */
	@SuppressWarnings("unchecked")
	private static JSONObject updateJson(Object object) 
	{
		JSONObject initialCell = (JSONObject) object;
		
		initialCell.put("row", (int) (long) initialCell.get("y"));
		initialCell.put("col", (int) (long) initialCell.get("x"));
		initialCell.put("value", (int) (long) initialCell.get("value"));
		
		initialCell.remove("x");
		initialCell.remove("y");
		
		return initialCell;
	}
	
	
	/**
	 * 
	 * Sorts the list of initial cell {@link JSONObject} elements in row—order.
	 * 
	 * @param initialCells 			 : column—ordered list of initial cell JSON Objects
	 * 
	 * @return ArrayList<JSONObject> : row—ordered list of initial cell JSON Objects
	 * 
	 */
	private static ArrayList<JSONObject> sortInitialCells(ArrayList<JSONObject> initialCells)
	{
		Collections.sort(initialCells, new Comparator<JSONObject>() 
		{
			@Override
			public int compare(JSONObject cellOne, JSONObject cellTwo)
			{
				return ((Integer) cellOne.get("row")).compareTo((Integer) cellTwo.get("row"));
			}
		});
		
		return initialCells;
	}
}