package astikoor.client.render;

import org.lwjgl.opengl.GL11;

import astikoor.Astikoor;
import astikoor.client.model.ModelCarriage;
import astikoor.entity.EntityCarriage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderCarriage extends Render<EntityCarriage>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Astikoor.MODID, "textures/entity/carriage.png");
    protected ModelBase model = new ModelCarriage();

    public RenderCarriage(RenderManager renderManager)
    {
        super(renderManager);
        this.shadowSize = 1.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCarriage entity)
    {
        return TEXTURE;
    }

    @Override
    public void doRender(EntityCarriage entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entityYaw);
        this.bindEntityTexture(entity);

        if(this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.model.render(entity, partialTicks, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

        if(this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void setupRotation(float entityYaw)
    {
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double x, double y, double z)
    {
        GlStateManager.translate((float) x, (float) y + 1.6F, (float) z);
    }
}