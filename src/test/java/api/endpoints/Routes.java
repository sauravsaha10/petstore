package api.endpoints;

// User Create POST   https://petstore.swagger.io/v2/user
// User Get    Get    https://petstore.swagger.io/v2/user/wrwr
// User Update PUT    https://petstore.swagger.io/v2/user/afaf
// User Delete Delete https://petstore.swagger.io/v2/user/fef

public class Routes {

	public static String baseURL = "https://petstore.swagger.io/v2";

	// User Module
	public static String createUser = baseURL+ "/user";
	public static String getUser = baseURL+ "/user/{userName}";
	public static String updateUser = baseURL+ "/user/{userName}";
	public static String deleteUser = baseURL+ "/user/{userName}";
}
