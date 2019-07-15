package dcdmod.Patches;

import com.megacrit.cardcrawl.cards.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import dcdmod.DCDmod;



@SpirePatch(clz = AbstractCard.class, method = "renderPortraitFrame", paramtypez = { SpriteBatch.class, float.class, float.class })
public class FramePatch
{
    public static SpireReturn<Object> Prefix(final AbstractCard the_AbstractCard, final SpriteBatch sb, final float x, final float y) {
        if (the_AbstractCard.hasTag(DCDmod.SelectCard)) {
        	return (SpireReturn<Object>)SpireReturn.Return((Object)null);
        }
        return (SpireReturn<Object>)SpireReturn.Continue();
    }
}
