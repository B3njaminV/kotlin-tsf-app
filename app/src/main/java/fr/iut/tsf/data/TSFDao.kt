package fr.iut.tsf.data

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import fr.iut.tsf.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TSFDao {

    @Query("SELECT * FROM film")
    fun getAll(): Flow<List<Film>>

    @Query("SELECT * FROM film")
    fun getAllFlow(): Flow<List<Film>>

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