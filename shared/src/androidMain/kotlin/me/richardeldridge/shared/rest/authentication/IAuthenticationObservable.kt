package me.richardeldridge.shared.rest.authentication

interface IAuthenticationObservable {
    val observers: ArrayList<IAuthenticationObserver>

    /**
     * @param observer
     */
    fun add(observer: IAuthenticationObserver) {
        observers.add(observer)
    }

    /**
     * @param observer
     */
    fun remove(observer: IAuthenticationObserver) {
        observers.remove(observer)
    }

    fun sendUpdateEvent() {
        observers.forEach { it.update() }
    }
}
