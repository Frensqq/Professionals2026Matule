package com.example.matule_2026.Domain.UseCase

import com.example.networklibrary.domain.model.NetworkResult
import com.example.networklibrary.domain.model.Project
import com.example.networklibrary.domain.model.RequestAuth
import com.example.networklibrary.domain.model.RequestCart
import com.example.networklibrary.domain.model.RequestOrder
import com.example.networklibrary.domain.model.RequestProject
import com.example.networklibrary.domain.model.RequestProjectImage
import com.example.networklibrary.domain.model.RequestRegister
import com.example.networklibrary.domain.model.ResponseAuth
import com.example.networklibrary.domain.model.ResponseCart
import com.example.networklibrary.domain.model.ResponseOrder
import com.example.networklibrary.domain.model.ResponseProducts
import com.example.networklibrary.domain.model.ResponseRegister
import com.example.networklibrary.domain.model.ResponsesCart
import com.example.networklibrary.domain.model.ResponsesNews
import com.example.networklibrary.domain.model.ResponsesOrders
import com.example.networklibrary.domain.model.ResponsesProject
import com.example.networklibrary.domain.model.User
import com.example.networklibrary.domain.model.UsersAuth
import com.example.networklibrary.domain.repository.PBRepository


class UseCase(private val Repository: PBRepository) {
    suspend operator fun invoke(email: String, password: String): NetworkResult<ResponseAuth> {
        return Repository.authorizationUser(RequestAuth(email, password))
    }

    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String,
        name: String,
        surname: String,
        lastname: String,
        dateBithday: String,
        gender: String,
    ): NetworkResult<ResponseRegister> {
        return Repository.registration(
            RequestRegister(
                email,
                password,
                confirmPassword,
                name,
                surname,
                gender,
                dateBithday,
                lastname
            )
        )
    }


    suspend fun getUser(id_user: String):NetworkResult<User>{
        return Repository.viewUser(id_user)
    }
   suspend fun GetNews():NetworkResult<ResponsesNews>{
       return Repository.promoAndNews()
   }

    suspend fun CreateOrders(request: RequestOrder):NetworkResult<ResponseOrder>{
        return Repository.createOrder(request)
    }

    suspend fun CreateProject(request: RequestProject):NetworkResult<Project>{
        return Repository.createProject(request)
    }

    suspend fun AddCart(request: RequestCart):NetworkResult<ResponseCart>{
        return Repository.createBucket(request)
    }

    suspend fun viewCart(filter: String?):NetworkResult<ResponsesCart>{
        return Repository.listBucket(filter)
    }

    suspend fun deleteCart(id: String):NetworkResult<Unit>{
        return Repository.deleteBucket(id)
    }

    suspend fun getProduct(filter: String?):NetworkResult<ResponseProducts>{
        return Repository.listProduct(filter)
    }

    suspend fun getProject(filter: String?):NetworkResult<ResponsesProject>{
        return Repository.listProject(filter)
    }

    suspend fun getOrders(filter: String?):NetworkResult<ResponsesOrders>{
        return Repository.listOrders(filter)
    }

    suspend fun getProfile(id:String):NetworkResult<User>{
        return Repository.viewUser(id)
    }

    suspend fun returnIdToken(token: String):NetworkResult<UsersAuth>{
        return Repository.returnIdToken(token)
    }
    suspend fun logout(token: String, id_token:String):NetworkResult<Unit>{
        return Repository.logout(token,id_token)
    }

    suspend fun changeCart(id_bucket: String, request: RequestCart):NetworkResult<ResponseCart>{
        return Repository.redactBucket(id_bucket, request)
    }

    suspend fun createProjectWithImage(
        title: String,
        typeProject: String,
        user_id: String,
        dateStart: String,
        dateEnd: String,
        gender: String,
        description_source: String,
        category: String,
        imageUri: android.net.Uri?,
        imageFileName: String = ""
    ): NetworkResult<Project> {
        return Repository.createProjectWithImage(
            request = RequestProjectImage(
                title = title,
                typeProject = typeProject,
                user_id = user_id,
                dateStart = dateStart,
                dateEnd = dateEnd,
                gender = gender,
                description_source = description_source,
                category = category,
                imageUri = imageUri,
                imageFileName = imageFileName
            )
        )
    }

}
