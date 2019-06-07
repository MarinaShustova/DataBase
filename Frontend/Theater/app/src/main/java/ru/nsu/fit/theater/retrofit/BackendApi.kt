@file:Suppress("unused")

package ru.nsu.fit.theater.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import ru.nsu.fit.theater.model.Actor
import ru.nsu.fit.theater.model.PerformanceInfo
import ru.nsu.fit.theater.model.Producer
import ru.nsu.fit.theater.model.Spectacle
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
    fun getAuthorById(@Path("id") id: Int): Call<IdAuthorData>

    @GET("/authors/get")
    fun getAuthors(): Call<List<IdAuthorData>>

    @GET("/authors/country")
    fun getAuthorsOfCountry(@Query("name") name: String): Call<List<IdAuthorData>>

    @GET("/authors")
    fun getAuthorsOfCentury(@Query("century") century: Int): Call<List<IdAuthorData>>

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

    @GET("/features")
    fun getFeatures(
            @Query("from") from: Int,
            @Query("size") size: Int
    ): Call<List<FeatureData>>

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
    @POST("/performances/create")
    fun createPerformance(
            @Query("pr_des") designer: Int,
            @Query("pr_dir") director: Int,
            @Query("pr_cond") conductor: Int,
            @Query("season") season: Int,
            @Query("spectacle_id") spectacle_id: Int
    ): Call<ResponseBody>

    @GET("/performances")
    fun getPerformances(
            @Query("num") from: Int,
            @Query("size") size: Int
    ): Call<List<PerformanceData>>

    @GET("/performances/{id}")
    fun getPerformance(@Path("id") id: Int): Call<PerformanceData>

    @POST("/performances/update/{id}")
    fun updatePerformance(
            @Path("id") id: Int,
            @Query("pr_des") designer: Int,
            @Query("pr_dir") director: Int,
            @Query("pr_cond") conductor: Int,
            @Query("season") season: Int,
            @Query("spectacle_id") spectacle_id: Int
    ): Call<ResponseBody>

    @POST("/performances/delete/{id}")
    fun deletePerformance(@Path("id") id: Int): Call<ResponseBody>

    @POST("/performances/link/{id}")
    fun addRoleToPerformance(
            @Path("id") perfId: Int,
            @Query("id2") roleId: Int
    ): Call<ResponseBody>

    @GET("/performances/spectacle/{id}")
    fun getPerformanceInfo(@Path("id") id: Int): Call<PerformanceInfo>
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
    @POST("/roles/create")
    fun createRole(@Query("name") name: String): Call<ResponseBody>

    @GET("/roles")
    fun getRoles():Call<List<RoleData>>

    @GET("/roles/{id}")
    fun getRoleById(@Path("id") id: Int): Call<RoleData>

    @POST("/roles/update/{id}")
    fun updateRole(
            @Path("id") id: Int,
            @Query("name") name: String
    ): Call<ResponseBody>

    @POST("/roles/delete/{id}")
    fun deleteRole(@Path("id") id: Int): Call<ResponseBody>

    @POST("/rolse/link/{id}")
    fun addFeatureToRole(
            @Path("id") roleId: Int,
            @Query("id2") featureId: Int
    ): Call<ResponseBody>
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
    fun getSpectacle(@Path("id") d: Int): Call<Spectacle>

    @GET("/spectacles/all")
    fun getSpectacles(): Call<List<Spectacle>>

    @GET("/spectacles/genre")
    fun getSpectaclesOfGenre(@Query("name") genre: String): Call<List<Spectacle>>

    @GET("/spectacles/author")
    fun getSpectaclesOfAuthor(@Query("id") id: Int): Call<List<Spectacle>>

    @GET("/spectacles/country")
    fun getSpectaclesOfCountry(@Query("name") country: String): Call<List<Spectacle>>

    @GET("/spectacles/get")
    fun getSpectaclesOfCentury(@Query("century") century: Int): Call<List<Spectacle>>

    @GET("/spectacles/get/")
    fun getSpectaclesOfPeriod(
            @Query("start") from: Timestamp,
            @Query("end") to: Timestamp
    ): Call<List<Spectacle>>

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
    @POST("/tours/create")
    fun createConcreteTour(
            @Query("city") city: String,
            @Query("start") start: String,
            @Query("finish") finish: String
    ): Call<ResponseBody>

    @GET("/tours")
    fun getConcreteTours(): Call<List<TourData>>

    @GET("/tours/{id}")
    fun getTourById(@Path("id") id: Int): Call<TourData>

    @POST("/tours/{id}")
    fun updateTour(
            @Path("id") id: Int,
            @Query("city") city: String,
            @Query("start") start: String,
            @Query("finish") finish: String
    ): Call<ResponseBody>

    @POST("/tours/delete/{id}")
    fun deleteTour(@Path("id") id: Int): Call<ResponseBody>

    @POST("/tours/link/{id}")
    fun addConcreteTourToPerformance(
            @Path("id") tourId: Int,
            @Query("id2") performanceId: Int
    ): Call<ResponseBody>

    @GET("/tours/troupeBySpectacle/{id}")
    fun getTourTroupe(
            @Path("id") id: Int,
            @Query("start") start: String,
            @Query("finish") finish: String
    ): Pair<List<Actor>, List<Producer>>
    //endregion
}