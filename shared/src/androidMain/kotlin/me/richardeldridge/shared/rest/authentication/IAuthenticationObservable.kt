package me.richardeldridge.shared.rest.authentication

interface  IAuthenticationObservable {
    val observers: ArrayList<IAuthenticationObserver>

    fun add(observer: IAuthenticationObserver) {
        observers.add(observer)
    }

    fun remove(observer: IAuthenticationObserver) {
        observers.remove(observer)
    }

    fun sendUpdateEvent() {
        observers.forEach { it.update() }
    }
}