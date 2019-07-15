package dcdmod.Patches;

import com.megacrit.cardcrawl.cards.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import dcdmod.DCDmod;



@SpirePatch(clz = AbstractCard.class, method = "renderEnergy", paramtypez = { SpriteBatch.class })
public class EnergyPatch
{
    public static SpireReturn<Object> Prefix(final AbstractCard the_AbstractCard, final SpriteBatch sb) {
        if (the_AbstractCard.hasTag(DCDmod.RiderCard)) {
            return (SpireReturn<Object>)SpireReturn.Return((Object)null);
        }
        return (SpireReturn<Object>)SpireReturn.Continue();
    }
}
