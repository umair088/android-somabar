package com.pyntail.somabar.retrofit;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.EncodedPath;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

import com.pyntail.somabar.entities.DiscoverDrinkResponse;
import com.pyntail.somabar.entities.FavoriteObject;
import com.pyntail.somabar.entities.LikeObject;
import com.pyntail.somabar.entities.User;
import com.pyntail.somabar.entities.request.Facebook;
import com.pyntail.somabar.entities.request.LoginObject;
import com.pyntail.somabar.entities.request.SignUpEntity;
import com.pyntail.somabar.entities.request.UserRecipesObject;

// To do : make callbacks

public interface WebService {

	@Multipart
	@POST("/api/upload/images")
	public void uploadImage(@Part("imageType") TypedFile imgToPost,
			Callback<SignUpEntity> callback);

	@PUT("/api/userrecipes")
	public void getAllRecipes(@Header("basic userid") String token,
			@Body UserRecipesObject userRecipesObject,
			CallbackRetrofit<User> callback);

	
	
	@PUT("/api/UserRecipes")
	public void getUserRecipes(@Header("Authorization") String base64encodedValue,
			@Field("userId") int userId, CallbackRetrofit<User> callback);

	
	@GET("/api/userrecipes/for/{userId}")
	public void getRecipesForMyDrinks(
			@EncodedPath("userId") int userId,
			@Header("Authorization") String base64encodedValue,
			CallbackRetrofit<ArrayList<DiscoverDrinkResponse>> callback);
	
	
	
	@POST("/api/recipes/{id}/fav")
	public void markFavourite(
			@EncodedPath("id") int id,
			@Header("Authorization") String base64encodedValue,
			CallbackRetrofit<FavoriteObject> callback);
	
	
	
	@GET("/api/userrecipes")
	public void searchRecipies(@Query("recipeId") int recipeId,
			@Query("count") int count, @Query("searchText") String searchText,
			CallbackRetrofit<FavoriteObject> callback);
	
	
	@POST("/api/recipes/{id}/like")
	public void markLike(
			@EncodedPath("id") int id,
			@Header("Authorization") String base64encodedValue,
			CallbackRetrofit<LikeObject> callback);
	
	@POST("/api/recipes/{id}/unlike")
	public void markUnLike(
			@EncodedPath("id") int id,
			@Header("Authorization") String base64encodedValue,
			CallbackRetrofit<LikeObject> callback);
	
	
	
	@DELETE("/api/userrecipes/{recipeId}")
	public void deleteRecipes(
			@EncodedPath("recipeId") int recipeId,
			@Header("Authorization") String base64encodedValue,
			CallbackRetrofit<User> callback);
	
	
	

	@GET("/api/recipes")
	public void getRecipesForPremium(@Query("recipeId") int recipeId,
			@Query("count") int count, @Query("since") Double since,
			CallbackRetrofit<ArrayList<DiscoverDrinkResponse>> callback);
	
	
	

	@POST("/api/users/forgotpassword")
	public void forgotPassword(
			@Header("Authorization") String base64encodedValue,
			@Body LoginObject userObject,
			CallbackRetrofit<User> callback);
	
	@POST("/api/users/forgotpassword")
	public void forgotPassword(
			@Body LoginObject userObject,
			CallbackRetrofit<User> callback);
	

	@POST("/api/users/login")
	public void loginUser(@Header("application") String header,
			@Body LoginObject userObject, CallbackRetrofit<User> callback);

	@PUT("/api/users/register")
	public void registerUser(@Header("application") String header,
			@Body SignUpEntity registerObject, CallbackRetrofit<User> callback);
	
	
	
	@POST("/api/users/login/fb")
	public void loginUserByFacebook(@Header("application") String header,
			@Body Facebook userObject, CallbackRetrofit<User> callback);
	

}
