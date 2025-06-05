package app.virtual_games.sudoku.controllers;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyStore;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.virtual_games.sudoku.exceptions.ApiCertificateException;
import app.virtual_games.sudoku.exceptions.ApiResponseException;
import app.virtual_games.sudoku.exceptions.SudokuPuzzleException;
import app.virtual_games.sudoku.models.ApiResponse;
import app.virtual_games.sudoku.models.ApiResponse.Square;

/**
 * Main controller for sudoku API.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class ApiController
{
  private static final String BASE_API_URL = "https://www.cs.utep.edu/cheon/ws/sudoku/new/?size=9&level=%d";
  private static final String TRUST_STORE_PASSWORD = "mypassword";

  private ApiController()
  {
  }

  /**
   * Retrieves sudoku puzzle with the corresponding difficulty.
   *
   * @param difficulty : unique identifier for the puzzle difficulty (e.g. 1 -> Easy)
   * @return List<JSONObject> : list of initial cell JSON Objects
   * @throws SudokuPuzzleException
   */
  public static List<Square> getSudokuPuzzle(int difficulty) throws SudokuPuzzleException
  {
    try
    {
      HttpClient client = ApiController.initializeHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format(BASE_API_URL, difficulty)))
          .timeout(Duration.ofSeconds(10)).GET().build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() == 200)
      {

        ObjectMapper mapper = new ObjectMapper();
        ApiResponse body = mapper.readValue(response.body(), ApiResponse.class);

        return ApiController.getInitialCells(body.getSquares());
      }

      throw new ApiResponseException(String.format("Response return status code %d", response.statusCode()));
    } catch (Exception e)
    {
      throw new SudokuPuzzleException(String.format("Failed to Load a Sudoku Puzzle. Reason: %s", e.getMessage()));
    }

  }

  /** Private Helper Methods **/

  /**
   * Initializes HTTP client with SSL certificate for sudoku API from embedded trust store.
   *
   * @return HttpClient : HTTP client
   * @throws ApiCertificateException
   */
  private static HttpClient initializeHttpClient() throws ApiCertificateException
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
   * Sorts a list of initial sudoku cells by row.
   *
   * @param squares : column-ordered list of initial sudoku cells
   * @return List<Square> : row-ordered list of initial sudoku cells
   */
  private static List<Square> getInitialCells(List<Square> squares)
  {
    Collections.sort(squares, (cellOne, cellTwo) -> (cellOne.getY() - cellTwo.getY()));
    return squares;
  }

}
