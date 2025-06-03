package app.virtual_games.sudoku.controllers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyStore;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.virtual_games.sudoku.exceptions.ApiCertificateException;
import app.virtual_games.sudoku.exceptions.ApiConnectionException;
import app.virtual_games.sudoku.exceptions.ApiResponseException;
import app.virtual_games.sudoku.exceptions.SudokuPuzzleException;

/**
 * Main controller for sudoku API.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class ApiController
{
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private static final String BASE_API_URL = "https://www.cs.utep.edu/cheon/ws/sudoku/new/?size=9&level=%d";
  private static final String TRUST_STORE_PASSWORD = "mypassword";

  // JSON-mapped classes
  public static class SudokuResponse
  {
    public boolean response;
    public int size;
    public List<Square> squares;
  }

  public static class Square
  {
    public int x;
    public int y;
    public int value;
  }

  private ApiController()
  {
  }

  /**
   * Retrieves a sudoku puzzle of the requested difficulty.
   *
   * @param difficulty : unique identifier for the puzzle difficulty (e.g. 1 -> Easy)
   * @return List<JSONObject> : list of initial cell JSON Objects
   * @throws SudokuPuzzleException
   */
  public static List<Square> getSudokuPuzzle(int difficulty) throws SudokuPuzzleException
  {
    try
    {
      HttpClient client = ApiController.initHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format(BASE_API_URL, difficulty)))
          .timeout(Duration.ofSeconds(10)).GET().build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() == 200)
      {

        ObjectMapper mapper = new ObjectMapper();
        SudokuResponse sudoku = mapper.readValue(response.body(), SudokuResponse.class);
        ArrayList<Square> initialCells = ApiController.getInitialCells(sudoku.squares);

        System.out.println("initial cells" + initialCells.toString());

        return initialCells;
      }

      throw new ApiResponseException(String.format("Response return status code %d", response.statusCode()));
    }
    // TODO: figure this out
    // catch (InterruptedException e)
    // {
    // LOGGER.log(Level.SEVERE, String.format("InterruptedException: %s", e.getMessage()));
    // Thread.currentThread().interrupt();
    // }
    catch (Exception e)
    {
      throw new SudokuPuzzleException(String.format("Failed to Load a Sudoku Puzzle. Reason: %s", e.getMessage()));
    }

  }

  /** Private Helper Methods **/

  /**
   * Initializes HTTP client with SSL certificate for sudoku API from embedded trust store.
   *
   * @throws ApiCertificateException
   * @return HttpClient : HTTP client
   */
  private static HttpClient initHttpClient() throws ApiCertificateException
  {
    try
    {
      InputStream trustStoreStream = ApiController.class.getResourceAsStream("/api_truststore.jks");

      if (trustStoreStream == null)
      {
        throw new ApiCertificateException("Trust store not found in resources.");
      }

      KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
      trustStore.load(trustStoreStream, TRUST_STORE_PASSWORD.toCharArray());

      TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      tmf.init(trustStore);

      SSLContext sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, tmf.getTrustManagers(), null);

      return HttpClient.newBuilder().sslContext(sslContext).build();
    } catch (Exception e)
    {
      throw new ApiCertificateException(
          String.format("Failed to set up custom trust store. Reason: %s", e.getMessage()));
    }
  }

  /**
   * Retrieves a row—ordered list of initial cell {@link JSONObject} elements.
   *
   * @throws ApiResponseException
   * @param response : multi—line string of initial cells
   * @return ArrayList<JSONObject> : list of initial cell JSON Objects
   */
  private static ArrayList<Square> getInitialCells(List<Square> squares) throws ApiResponseException
  {
    // return sortInitialCells(mapJsonArray(squares));
    return sortInitialCells(squares);
  }

  /**
   * Retrieves an updated list of initial cell {@link JSONObject} elements.
   *
   * @param jsonArray : list of initial cell JSON Objects
   * @return ArrayList<JSONObject> : list of initial cell JSON Objects
   */
  // TODO: clean up
  // private static ArrayList<JSONObject> mapJsonArray(ArrayList<JSONObject> jsonArray)
  // {
  // return
  // jsonArray.stream().map(ApiController::updateJson).collect(Collectors.toCollection(ArrayList::new));
  // }

  /**
   * Updates the key—value pairs for a {@link JSONObject} element.
   *
   * @param object : initial cell Object
   * @return JSONObject : initial cell JSON Object
   */
  // TODO: clean up
  // @SuppressWarnings("unchecked")
  // private static Object updateJson(Object object)
  // {
  // var initialCell = object;

  // initialCell["row"] = (int) (long) initialCell["y")
  // initialCell.("row", (int) (long) initialCell.get("y"));
  // initialCell.put("col", (int) (long) initialCell.get("x"));
  // initialCell.put("value", (int) (long) initialCell.get("value"));

  // initialCell.remove("x");
  // initialCell.remove("y");

  // return initialCell;
  // }

  /**
   * Sorts the list of initial cell {@link JSONObject} elements in row—order.
   *
   * @param initialCells : column—ordered list of initial cell JSON Objects
   * @return ArrayList<JSONObject> : row—ordered list of initial cell JSON Objects
   */
  private static ArrayList<Square> sortInitialCells(List<Square> squares)
  {
    Collections.sort(squares, (cellOne, cellTwo) -> ((Integer) cellOne.y).compareTo((Integer) cellTwo.y));
    return new ArrayList<Square>(squares);
  }
}
