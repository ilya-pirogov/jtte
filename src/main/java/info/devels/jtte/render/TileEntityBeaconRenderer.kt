package info.devels.jtte.render

import info.devels.jtte.entities.TileEntityBeacon
import info.devels.jtte.models.BeaconModel
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12


class TileEntityBeaconRenderer(protected val model: BeaconModel, protected val texture: ResourceLocation) : TileEntitySpecialRenderer() {
    fun renderTileEntityAt(tile: TileEntityBeacon, x: Double, y: Double, z: Double, n: Float) {
        this.bindTexture(texture)

        GL11.glPushMatrix()
        GL11.glEnable(GL12.GL_RESCALE_NORMAL)
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)

        var dx = x.toFloat()
        var dy = y.toFloat() + 1.5f
        var dz = z.toFloat()

        GL11.glTranslatef(dx, dy, dz)
        GL11.glScalef(1.0f, -1.0f, -1.0f)

        GL11.glRotatef(180f, 0f, 1f, 0f)
        dx = -0.5f
        dy = 0f
        dz = +0.5f
        GL11.glTranslatef(dx, dy, dz)

        model.rotateCube((tile.angleCube + tile.rtCube * n).toFloat())
        model.rotateBottomPieces((tile.angleBottomPieces + tile.rtBottomPieces * n).toFloat())
        model.rotateMiddlePieces((tile.angleMiddlePieces + tile.rtMiddlePieces * n).toFloat())
        model.rotateTopPieces((tile.angleTopPieces + tile.rtTopPieces * n).toFloat())

        model.renderAll()

        GL11.glDisable(GL11.GL_BLEND)
        GL11.glDisable(GL12.GL_RESCALE_NORMAL)
        GL11.glPopMatrix()
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
    }

    override fun renderTileEntityAt(tile: TileEntity, x: Double, y: Double, z: Double, n: Float) {
        renderTileEntityAt(tile as TileEntityBeacon, x, y, z, n)
    }
}
