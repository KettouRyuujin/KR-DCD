package dcdmod.Patches;

import com.megacrit.cardcrawl.cards.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import dcdmod.DCDmod;



@SpirePatch(clz = AbstractCard.class, method = "renderType", paramtypez = { SpriteBatch.class })
public class TypePatch
{
    public static SpireReturn<Object> Prefix(final AbstractCard the_AbstractCard, final SpriteBatch sb) {
        if (the_AbstractCard.hasTag(DCDmod.SelectCard)) {
            return (SpireReturn<Object>)SpireReturn.Return((Object)null);
        }
        return (SpireReturn<Object>)SpireReturn.Continue();
    }
}
