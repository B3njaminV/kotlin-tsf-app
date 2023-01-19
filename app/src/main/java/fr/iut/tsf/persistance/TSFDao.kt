package fr.iut.tsf.persistance

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import fr.iut.tsf.model.*

@Dao
interface TSFDao {

    @Query("SELECT * FROM film")
    fun getAll(): List<FilmEntity>

    @Query("SELECT * FROM film WHERE id = :id")
    fun findById(id: Long): FilmEntity

    @Insert(onConflict = REPLACE)
    fun insert(dog: FilmEntity)

    @Insert
    fun insertAll(vararg dogs: FilmEntity)

    @Update(onConflict = REPLACE)
    fun update(dog: FilmEntity)

    @Delete
    fun delete(dog: FilmEntity)
}