package theater.model.data

import theater.model.Role

data class RoleData(val name: String) {
    constructor(r: Role): this(r.name)
}