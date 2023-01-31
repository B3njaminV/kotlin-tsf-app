package fr.iut.tsf.data

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import fr.iut.tsf.data.entity.FilmEntity
import fr.iut.tsf.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TSFDao {

    @Query("SELECT * FROM film")
    fun getAll(): Flow<List<FilmEntity>>
    @Query("SELECT * FROM film WHERE id = :id")
    fun findById(id: Long): FilmEntity
    @Insert(onConflict = REPLACE)
    fun insert(id: FilmEntity)
    @Insert(onConflict = REPLACE)
    fun insertAll(id: List<FilmEntity>)
    @Update(onConflict = REPLACE)
    fun update(id: FilmEntity)
    @Delete
    fun delete(id: FilmEntity)
}