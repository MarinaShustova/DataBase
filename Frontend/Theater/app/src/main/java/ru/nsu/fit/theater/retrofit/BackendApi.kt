@file:Suppress("unused")

package ru.nsu.fit.theater.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import ru.nsu.fit.theater.retrofit.model.*
import java.sql.Timestamp

interface BackendApi {
    //region Actors
    @GET("/actors/{id}")
    fun getActorById(@Path("id") id: Int): Call<ActorData>

    @GET("/actors/fio")
    fun getActorByName(@Query("fio") fio: String): Call<ActorData>

    @POST("/actors/create")
    fun createActor(@Body data: ActorData): Call<String>

    @DELETE("/actors/{id}")
    fun deleteActor(@Path("id") id: Int): Call<String>

    @POST("/actors/update")
    fun updateActor(@Body data: ActorData): Call<String>

    @GET("/actors")
    fun getActors(): Call<List<ActorData>>

    @GET("/actors/sex")
    fun getActorsBySex(@Query("sex") sex: String): Call<List<ActorData>>

    @GET("/actors/exp")
    fun getActorsByExperience(@Query("exp") exp: Int): Call<List<ActorData>>

    @GET("/actors/birth")
    fun getActorsByBirthDate(@Query("birth") birth: String): Call<List<ActorData>>

    @GET("/actors/age")
    fun getActorsByAge(@Query("age") age: Int): Call<List<ActorData>>

    @GET("/actors/children_amount")
    fun getActorsByChildrenAmount(@Query("children_amount") childrenAmount: Int): Call<List<ActorData>>

    @GET("/actor/salary")
    fun getActorsBySalary(@Query("salary") salary: Int): Call<List<ActorData>>

    @GET("/actors/ranked")
    fun getRankedActors(): Call<Pair<List<ActorData>, Int>>

    @GET("/actors/ranked/sex")
    fun getRankedActorsBySex(@Query("sex") sex: String): Call<Pair<List<ActorData>, Int>>

    @GET("/actors/ranked/age")
    fun getRankedActorsByAge(@Query("age") age: Int): Call<Pair<List<ActorData>, Int>>

    @GET("/actors/ranked/contests")
    fun getRankedActorsByContests(@Body contests: List<String>): Call<Pair<List<ActorData>, Int>>

    @GET("/actors/roles/{id}")
    fun getActorRoles(@Path("id") id: Int): Call<List<RoleData>>

    @GET("/actors/roles/age/{id}")
    fun getActorRolesByAge(
            @Path("id") id: Int,
            @Query("age") age: Int
    ): Call<List<RoleData>>

    @GET("/actors/roles/producer/{id}")
    fun getActorRolesByProducer(
            @Path("id") id: Int,
            @Query("producer") producerFio: String
    ): Call<List<RoleData>>

    @GET("/actors/roles/period/{id}")
    fun getActorRolesByPeriod(
            @Path("id") id: Int,
            @Query("startDate") start: String,
            @Query("endDate") end: String
    ): Call<List<RoleData>>

    @GET("/actors/roles/genre/{id}")
    fun getActorRolesByGenre(
            @Path("id") id: Int,
            @Query("genreName") genre: String
    ): Call<List<RoleData>>
    //endregion

    //region Authors
    @POST("/authors/create")
    fun createAuthor(@Body author: AuthorData): Call<ResponseBody>

    @GET("/authors/{id}")
    fun getAuthorById(@Path("id") id: Int): Call<AuthorData>

    @GET("/authors/get")
    fun getAuthors(): Call<List<AuthorData>>

    @GET("/authors/country")
    fun getAuthorsOfCountry(@Query("name") name: String): Call<List<AuthorData>>

    @GET("/authors")
    fun getAuthorsOfCentury(@Query("century") century: Int): Call<List<AuthorData>>

    @POST("/authors/update/{id}")
    fun updateAuthor(@Path("id") id: Int, @Body data: AuthorData): Call<ResponseBody>

    @POST("/authors/delete/{id}")
    fun deleteAuthor(@Path("id") id: Int): Call<ResponseBody>
    //endregion

    //region Country
    @POST("/country/create")
    fun createCountry(@Query("name") name: String): Call<Int>

    @GET("/country/{id}")
    fun getCountry(@Path("id") id: Int): Call<CountryData>

    @GET("/country/get")
    fun getCountries(): Call<List<CountryData>>

    @POST("/country/update")
    fun updateCountry(@Body data: CountryData): Call<ResponseBody>
    //endregion

    //region Employees
    @GET("/employees/{id}")
    fun getEmployeeById(@Path("id") id: Int): Call<EmployeeData>

    @GET("/employees/fio")
    fun getEmployeeByName(@Query("fio") fio: String): Call<EmployeeData>

    @GET("/employees")
    fun getEmployees(): Call<List<EmployeeData>>

    @GET("/employees/sex")
    fun getEmployeesBySex(@Query("sex") sex: String): Call<List<EmployeeData>>

    @GET("/employees/exp")
    fun getEmployeesByExp(@Query("exp") exp: Int): Call<List<EmployeeData>>

    @GET("/employees/birth")
    fun getEmployeesByBirth(@Query("birth") birth: String): Call<List<EmployeeData>>

    @GET("/employees/age")
    fun getEmployeesByAge(@Query("age") age: Int): Call<List<EmployeeData>>

    @GET("/employees/children_amount")
    fun getEmployeesByChildrenAmount(@Query("children_amount") amount: Int): Call<List<EmployeeData>>

    @GET("/employees/salary")
    fun getEmployeesBySalary(@Query("salary") salary: Int): Call<List<EmployeeData>>
    //endregion

    //region Features
    @POST("/features/create")
    fun createFeature(
            @Query("name") name: String,
            @Query("value") value: String
    ): Call<ResponseBody>

    //TODO: GET FEATURES!!!!!!

    @POST("/features/update/{id}")
    fun updateFeature(
            @Path("id") id: Int,
            @Query("name") name: String,
            @Query("value") value: String
    ): Call<ResponseBody>

    @POST("/features/delete/{id}")
    fun deleteFeature(@Path("id") id: Int): Call<ResponseBody>
    //endregion

    //region Genres
    @POST("/genres/create")
    fun createGenre(@Query("name") name: String): Call<GenreData>

    @GET("/genres/{id}")
    fun getGenre(@Path("id") id: Int): Call<GenreData>

    @GET("/genres/get")
    fun getGenres(): Call<List<GenreData>>

    @POST("/genres/update")
    fun updateGenre(@Body genre: GenreData): Call<ResponseBody>

    @POST("/genres/delete/{id}")
    fun deleteGenre(@Path("id") id: Int): Call<ResponseBody>
    //endregion

    //region Musicians
    @GET("/musicians/{id}")
    fun getMusicianById(@Path("id") id: Int): Call<MusicianData>

    @POST("/musicians/create")
    fun createMusician(@Body data: MusicianData): Call<ResponseBody>

    @DELETE("/musicians/{id}")
    fun deleteMusician(@Path("id") id: Int): Call<ResponseBody>

    @POST("/musicians/update")
    fun updateMusician(@Body data: MusicianData): Call<ResponseBody>

    @GET("/musicians/fio")
    fun getMusicianByName(@Query("fio") fio: String): Call<MusicianData>

    @GET("/musicians")
    fun getMusicians(): Call<List<MusicianData>>

    @GET("/musicians/sex")
    fun getMusiciansBySex(@Query("sex") sex: String): Call<List<MusicianData>>

    @GET("/musicians/exp")
    fun getMusiciansByExp(@Query("exp") exp: Int): Call<List<MusicianData>>

    @GET("/musicians/birth")
    fun getMusiciansByBirthDate(@Query("birth") date: String): Call<List<MusicianData>>

    @GET("/musicians/age")
    fun getMusiciansByAge(@Query("age") age: Int): Call<List<MusicianData>>

    @GET("/musicians/children_amount")
    fun getMusiciansByChildrenAmount(@Query("children_amount") amount: Int): Call<List<MusicianData>>

    @GET("/musicians/salary")
    fun getMusiciansBySalary(@Query("salary") salary: Int): Call<List<MusicianData>>
    //endregion

    //region Performances
    //TODO: implement performances!!!
    //endregion

    //region Producers
    @GET("/producers/{id}")
    fun getProducerById(@Path("id") id: Int): Call<ProducerData>

    @GET("/producers/fio")
    fun getProducerByFio(@Query("fio") fio: String): Call<ProducerData>

    @GET("/producers")
    fun getProducers(): Call<List<ProducerData>>

    @POST("/producers/create")
    fun createProducer(@Body data: ProducerData): Call<ResponseBody>

    @DELETE("/producers/{id}")
    fun deleteProducer(@Path("id") id: Int): Call<ResponseBody>

    @POST("/producers/update")
    fun updateProducer(@Body data: ProducerData): Call<ResponseBody>
    //endregion

    //region Roles
    //TODO: implement roles!!!
    //endregion

    //region Servants
    @GET("/servants/{id}")
    fun getServantById(@Path("id") id: Int): Call<ServantData>

    @GET("/servants/fio")
    fun getServantByFio(@Query("fio") fio: String): Call<ServantData>

    @GET("/servants")
    fun getServants(): Call<ServantData>

    @POST("/servants/create")
    fun createServant(@Body data: ServantData): Call<ResponseBody>

    @DELETE("/servants/{id}")
    fun deleteServant(@Path("id") id: Int): Call<ResponseBody>

    @POST("/servants/update")
    fun updateServant(@Body data: ServantData): Call<ResponseBody>
    //endregion

    //region Shows
    @POST("/shows/create")
    fun createShow(@Body data: ShowData): Call<ResponseBody>

    @POST("/shows/update/{id}")
    fun updateShow(@Path("id") id: Int, @Body data: ShowData): Call<ResponseBody>

    @GET("/shows/{id}")
    fun getShow(@Path("id") id: Int): Call<IdShowData>

    @GET("/shows/get")
    fun getShows(): Call<List<IdShowData>>

    @POST("/shows/delete/{id}")
    fun deleteShow(@Path("id") id: Int): Call<ResponseBody>
    //endregion

    //region Spectacles
    @POST("/spectacles/create")
    fun createSpectacle(@Body data:SpectacleData): Call<ResponseBody>

    @POST("/spectacles/create/author")
    fun createAuthorOfSpectacle(
            @Query("authorId") authorId: Int,
            @Query("spectacleName") specName: String
    ): Call<ResponseBody>

    @GET("/spectacles/{id}")
    fun getSpectacle(@Path("id") d: Int): Call<SpectacleData>

    @GET("/spectacles/all")
    fun getSpectacles(): Call<List<IdSpectacleData>>

    @GET("/spectacles/genre")
    fun getSpectaclesOfGenre(@Query("name") genre: String): Call<List<IdSpectacleData>>

    @GET("/spectacles/author")
    fun getSpectaclesOfAuthor(@Query("id") id: Int): Call<List<IdSpectacleData>>

    @GET("/spectacles/country")
    fun getSpectaclesOfCountry(@Query("name") country: String): Call<List<IdSpectacleData>>

    @GET("/spectacles/get")
    fun getSpectaclesOfCentury(@Query("century") century: Int): Call<List<IdSpectacleData>>

    @GET("/spectacles/get/")
    fun getSpectaclesOfPeriod(
            @Query("start") from: Timestamp,
            @Query("end") to: Timestamp
    ): Call<List<IdSpectacleData>>

    @POST("/spectacles/update")
    fun updateSpectacle(@Query("id") id: Int, @Body data: SpectacleData): Call<ResponseBody>

    @POST("/spectacles/delete/{id}")
    fun deleteSpectacle(@Path("id") id: Int): Call<ResponseBody>
    //endregion

    //region Tickets
    @GET("/tickets/get/show")
    fun getTicketsOfShow(@Query("showId") id: Int): Call<List<TicketData>>

    @POST("/tickets/buy")
    fun buyTicket(
            @Query("row") row: Int,
            @Query("seat") seat: Int,
            @Query("price") price: Int,
            @Query("showId") showId: Int,
            @Query("previously") previously: Boolean
    ): Call<ResponseBody>
    //endregion

    //region Tours
    //TODO: implement tours!!!
    //endregion
}