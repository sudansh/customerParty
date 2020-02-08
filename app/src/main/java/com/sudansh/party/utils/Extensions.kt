package com.sudansh.party.utils

import com.google.gson.Gson

inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this, T::class.java)