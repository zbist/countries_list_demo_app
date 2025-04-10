package com.example.myapplication.domain.utils

sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>
}

inline fun <L, R> Either<L, R>.onFailure(fn: (failure: L) -> Unit): Either<L, R> =
    this.apply { if (this is Either.Left) fn(a) }

inline fun <L, R> Either<L, R>.onSuccess(fn: (success: R) -> Unit): Either<L, R> =
    this.apply { if (this is Either.Right) fn(b) }

fun <R : Any> R.toRight(): Either.Right<R> {
    return Either.Right(this)
}

fun <L : Exception> L.toLeft(): Either.Left<L> {
    return Either.Left(this)
}

fun <L : Any> L.toLeft(): Either.Left<L> {
    return Either.Left(this)
}