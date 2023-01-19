package fr.iut.tsf.persistance

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import fr.iut.tsf.model.*

@Dao
interface TSFDao {

    @Query("SELECT * FROM film")
    fun getAll(): List<Film>

    @Query("SELECT * FROM film WHERE id = :id")
    fun findById(id: Long): Film

    @Insert(onConflict = REPLACE)
    fun insert(dog: Film)

    @Insert
    fun insertAll(vararg dogs: Film)

    @Update(onConflict = REPLACE)
    fun update(dog: Film)

    @Delete
    fun delete(dog: Film)
}