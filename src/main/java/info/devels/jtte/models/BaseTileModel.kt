package info.devels.jtte.models

import net.minecraft.client.model.ModelBase


open class BaseTileModel : ModelBase() {
    fun renderAll() {
        this.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625f)
    }
}
