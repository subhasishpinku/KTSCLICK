package com.kits.kitsclick;
import android.content.Context;
import android.util.Log;

import com.kits.kitsclick.retrofit.APIRequests;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClientToken {
//    Context c;
//    public ApiClientToken(Context context) {
//        c = context;
//    }
    private static final String BASE_URL = Config.BASEURL2;
    private static APIRequests apiRequests;
    // Singleton Instance of APIRequests
    public static APIRequests getInstance() {
        if (apiRequests == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor(){
                        public Context context;
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request request= chain.request();
                            Request newRequest = request.newBuilder()
                                    .header("Authorization","Bearer" + " "
                                            + SharedPrefManager.getInstance(context).getKeyToken())
                                    .build();
                            Log.e("token",SharedPrefManager.getInstance(context).getKeyToken());
                            return chain.proceed(newRequest);
                        }
                    })
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiRequests = retrofit.create(APIRequests.class);

            return apiRequests;
        }
        else {
            return apiRequests;
        }
    }
}
