package app.virtual_games.sudoku.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import app.virtual_games.sudoku.exceptions.ApiConnectionException;
import app.virtual_games.sudoku.exceptions.ApiResponseException;
import app.virtual_games.sudoku.exceptions.SudokuPuzzleException;


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


  private ApiController() { }


  /**
   *
   * Retrieves a sudoku puzzle of the requested difficulty.
   *
   * @throws SudokuPuzzleException
   *
   * @param difficulty : unique identifier for the puzzle difficulty (e.g. 1 -> Easy)
   *
   * @return List<JSONObject> : list of initial cell JSON Objects
   *
   */
  public static List<JSONObject> getSudokuPuzzle(int difficulty) throws SudokuPuzzleException
  {
    try
    {
      var apiUrl = new URL(String.format(BASE_API_URL, difficulty));
      var connection = (HttpURLConnection) apiUrl.openConnection();

      ArrayList<JSONObject> initialCells = ApiController.getInitialCells(ApiController.sendRequest(connection));
      connection.disconnect();

      return initialCells;
    }
    catch (Exception e)
    {
      throw new SudokuPuzzleException("Failed to Load a Sudoku Puzzle.");
    }

  }


  /**  Private Helper Methods  **/


  /**
   *
   * Sends and processes the API request.
   *
   * @throws ApiConnectionException
   * @throws ApiResponseException
   *
   * @param connection : HTTP connection to the API
   *
   * @return String : API response
   *
   */
  private static String sendRequest(HttpURLConnection connection) throws ApiConnectionException, ApiResponseException
  {
    if (ApiController.getResponseCode(connection) == HttpURLConnection.HTTP_OK)
    {
      return ApiController.loadApiResponse(connection);
    }

    throw new ApiConnectionException("Failed to Connect to the Api.");
  }


  /**
   *
   * Retrieves the request response code.
   *
   * @throws ApiConnectionException
   *
   * @param connection : HTTP connection to the API
   *
   * @return int : response code (e.g. Success —> 200)
   *
   */
  private static int getResponseCode(HttpURLConnection connection) throws ApiConnectionException
  {
    try
    {
      connection.setRequestMethod("GET");
      connection.connect();

      return connection.getResponseCode();
    }
    catch (Exception e)
    {
      throw new ApiConnectionException("Failed to Connect to the Api.");
    }
  }


  /**
   *
   * Loads the API response.
   *
   * @throws ApiResponseException
   *
   * @param connection : HTTP connection to the API
   *
   * @return String : API response
   *
   */
  private static String loadApiResponse(HttpURLConnection connection) throws ApiResponseException
  {
    try
    {
      var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      var response = new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) { response.append(line); }

      reader.close();

      return response.toString();
    }
    catch (Exception e)
    {
      throw new ApiResponseException("Failed to Load Api Response.");
    }
  }


  /**
   *
   * Retrieves a row—ordered list of initial cell {@link JSONObject} elements.
   *
   * @throws ApiResponseException
   *
   * @param response : multi—line string of initial cells
   *
   * @return ArrayList<JSONObject> : list of initial cell JSON Objects
   *
   */
  private static ArrayList<JSONObject> getInitialCells(String response) throws ApiResponseException
  {
    return sortInitialCells(mapJsonArray(getJsonArray(response)));
  }


  /**
   *
   * Retrieves a column—ordered list of initial cell {@link JSONObject} elements.
   *
   * @throws ApiResponseException
   *
   * @param response : multi—line string of initial cells
   *
   * @return ArrayList<JSONObject> : list of initial cell JSON Objects
   *
   */
  @SuppressWarnings("unchecked")
  private static ArrayList<JSONObject> getJsonArray(String response) throws ApiResponseException
  {
    try
    {
      return (JSONArray) ((JSONObject) new JSONParser().parse(response)).get("squares");
    }
    catch (Exception e)
    {
      throw new ApiResponseException("Failed to Parse Api Response.");
    }
  }


  /**
   *
   * Retrieves an updated list of initial cell {@link JSONObject} elements.
   *
   * @param jsonArray : list of initial cell JSON Objects
   *
   * @return ArrayList<JSONObject> : list of initial cell JSON Objects
   *
   */
  private static ArrayList<JSONObject> mapJsonArray(ArrayList<JSONObject> jsonArray)
  {
    return jsonArray.stream()
                    .map(ApiController::updateJson)
                    .collect(Collectors.toCollection(ArrayList::new));
  }


  /**
   *
   * Updates the key—value pairs for a {@link JSONObject} element.
   *
   * @param object : initial cell Object
   *
   * @return JSONObject : initial cell JSON Object
   *
   */
  @SuppressWarnings("unchecked")
  private static JSONObject updateJson(Object object)
  {
    var initialCell = (JSONObject) object;

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
   * @param initialCells : column—ordered list of initial cell JSON Objects
   *
   * @return ArrayList<JSONObject> : row—ordered list of initial cell JSON Objects
   *
   */
  private static ArrayList<JSONObject> sortInitialCells(ArrayList<JSONObject> initialCells)
  {
    Collections.sort(initialCells, (cellOne, cellTwo) -> ((Integer) cellOne.get("row")).compareTo((Integer) cellTwo.get("row")));

    return initialCells;
  }
}
