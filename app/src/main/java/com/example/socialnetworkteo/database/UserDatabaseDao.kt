package com.example.socialnetworkteo.database

import androidx.room.*
import com.example.socialnetworkteo.models.User

@Dao
interface UserDatabaseDao {
    @Insert(entity = User::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * from users_information WHERE id = :key")
    suspend fun getId(key: Int): User?

    @Query("SELECT * FROM users_information")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT COUNT(*) FROM users_information LIMIT 1")
    suspend fun getSize(): Int?

    @Query("SELECT * from users_information LIMIT 1")
    suspend fun listEmpty(): User?

    @Delete
    suspend fun delete(user: User)
}