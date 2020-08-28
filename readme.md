# Android application for showing weather
The app is fully written in **Kotlin** programming language and contains 2 main screens:

1. Screen with list of current weather for several cities.
2. Screen that shows weather for chosen city for 10 days.

Second screen opens by clicking on some city from the first screen. Data is taken from [OpenWeather API](https://openweathermap.org/api).

Technologies and libs utilized:

1. **ViewModel** class for saving data during Activity Lifecycle events, such as screen rotations.
2. **Retrofit2** + **Okhttp3** for sending and processing queries. **RxJava3** makes it asynchronically.
3. Network logic is delegated to separate repository in order to make the code clean. Custom Kotlin extension are also used for processing data.
4. *ConstraintLayout, CardView, RecyclerView, RelativeLayout, LinearLayout...etc* are used in .XML markup.

Clone the repository and install **weather.apk** in order to launch the app. You can also download the .apk from [this link](https://yadi.sk/d/nNTlcNT78DIF0Q).