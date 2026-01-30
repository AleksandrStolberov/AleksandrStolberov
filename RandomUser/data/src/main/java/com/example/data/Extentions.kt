package com.example.data

import com.example.domain.Dob
import com.example.domain.Location
import com.example.domain.Name
import com.example.domain.Picture
import com.example.domain.Street
import com.example.domain.User

fun UserResponse.toDomain(): User {
    return User(
        gender = this.gender,
        name = Name(
            first = this.name.first,
            last = this.name.last
        ),
        location = Location(
            street = Street(
                number = this.location.street.toString(),
                name = this.location.street.name
            ),
            city = this.location.city,
            state = this.location.state,
            country = this.location.country
        ),
        email = this.email,
        dateOfBirth = Dob(
            date = this.dateOfBirth.date,
            age = this.dateOfBirth.age.toString()
        ),
        phone = this.phone,
        picture = Picture(
            large = this.picture.large,
            medium = this.picture.medium,
            thumbnail = this.picture.thumbnail
        ),
        nat = this.nat
    )
}