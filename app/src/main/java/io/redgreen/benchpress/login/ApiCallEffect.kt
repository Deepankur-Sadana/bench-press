package io.redgreen.benchpress.login

sealed class LoginEffect

data class ApiCallEffect(val request : LoginRequest) : LoginEffect()
data class NavigateEffect(val to : NavigateTo) : LoginEffect()

object SaveTokenEffect : LoginEffect()
object ClearFieldsEffect : LoginEffect()
object RetryEffect : LoginEffect()
