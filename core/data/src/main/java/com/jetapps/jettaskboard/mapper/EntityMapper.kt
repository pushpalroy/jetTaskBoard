package com.jetapps.jettaskboard.mapper

interface EntityMapper<Domain, Data> {

    // Data: Entity <-> Domain: Model
    fun mapToDomain(entity: Data): Domain
    fun mapToData(model: Domain): Data
}
