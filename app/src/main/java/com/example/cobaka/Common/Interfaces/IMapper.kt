package com.example.cobaka.Common.Interfaces

interface IMapper<in TFrom, out TTo> {
    fun map(from : TFrom) : TTo
}