package fr.iut.tsf.data

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import fr.iut.tsf.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TSFDao {
    @Query("SELECT * FROM film")
    fun getAll(): Flow<List<Film>>
    @Query("SELECT * FROM film WHERE id = :id")
    fun findById(id: Long): Film
    @Insert(onConflict = REPLACE)
    fun insert(id: Film)
    @Insert(onConflict = REPLACE)
    fun insertAll(id: List<Film>)
    @Update(onConflict = REPLACE)
    fun update(id: Film)
    @Delete
    fun delete(id: Film)
}