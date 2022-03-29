package me.richardeldridge.shared.rest

interface IUserDataObservable {
    val observers: ArrayList<IUserDataObserver>

    /**
     * @param observer
     */
    fun add(observer: IUserDataObserver) {
        observers.add(observer)
    }

    /**
     * @param observer
     */
    fun remove(observer: IUserDataObserver) {
        observers.remove(observer)
    }

    fun sendUpdateEvent() {
        observers.forEach { it.update() }
    }
}
