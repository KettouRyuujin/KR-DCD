package dcdmod.Patches;



import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
@SpirePatch(
        clz = AbstractCreature.class,
        method = "loseBlock",
        paramtypez = {}
)
public class BlockPatch {
    public BlockPatch() {
    }

    public static SpireReturn<Object> Prefix(AbstractCreature The_AbstractCreature) {
        //这是标志变量
//        Load.tree=false;
    	if(AbstractDungeon.player != null && AbstractDungeon.player.hasPower("KamenRideAgitoPower")) {
    		if(The_AbstractCreature.currentBlock>15) {
            	The_AbstractCreature.loseBlock(15);
        	}else{
        		The_AbstractCreature.loseBlock(The_AbstractCreature.currentBlock );
        	}
        }
    	else {
    		The_AbstractCreature.loseBlock(The_AbstractCreature.currentBlock );
    	}
        return SpireReturn.Return((Object)null);
    }
}
