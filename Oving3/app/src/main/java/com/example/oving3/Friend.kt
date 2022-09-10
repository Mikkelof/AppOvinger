package com.example.oving3;

import java.io.Serializable

class Friend(var name: String?, var dateOfBirth: String?): Serializable {
    override fun toString(): String {
        return "Name: " + name + ", Date of birth: " + dateOfBirth
    }
}