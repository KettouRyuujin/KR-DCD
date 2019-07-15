package dcdmod.Patches;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import dcdmod.Characters.Decade;
import dcdmod.Helper.SpecialFaizButton;
import dcdmod.Helper.SpecialHibikiTaiko;
import dcdmod.Helper.SpecialHibikiTaikoKey;
import dcdmod.Helper.SpecialHibikiTaikoScore;
import dcdmod.Helper.SpecialRideBooker;

public class RideSpecialTriggerPatch {
	

	
	
	
    public RideSpecialTriggerPatch() {}
    
    @SpirePatch(
    		cls = "basemod.abstracts.CustomPlayer",
            method = "renderOrb"
    )
    public static class RenderPatch {
        public RenderPatch() {}
        public static SpireReturn<Object> Prefix(AbstractPlayer the_AbstractPlayer,SpriteBatch sb) {
        if (AbstractDungeon.player instanceof Decade) {
            if (((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() instanceof MonsterRoom) && !the_AbstractPlayer.isDead)) {   
            	if(AbstractDungeon.player.hasPower("KamenRideDecadePower")||AbstractDungeon.player.hasPower("KamenRideRyukiPower")||AbstractDungeon.player.hasPower("KamenRideBladePower")) {         
            		SpecialRideBooker.update();
            		SpecialRideBooker.render(sb);
            	}
            	if(AbstractDungeon.player.hasPower("KamenRideHibikiPower")&&!AbstractDungeon.player.hasPower("HibikiKurenaiPower")){
            		SpecialHibikiTaikoKey.update();
            		SpecialHibikiTaikoKey.render(sb);
            		SpecialHibikiTaikoScore.update();
            		SpecialHibikiTaikoScore.render(sb);
            	}
            	if(AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
            		SpecialFaizButton.update();
            		SpecialFaizButton.render(sb);
            	}
            	return SpireReturn.Continue();
            }
            else {
            	return SpireReturn.Continue();
            }
        }	
		return SpireReturn.Continue();
        }
    }
    
    @SpirePatch(
            cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
    		method = "render"
    )
    public static class RenderPatch2 {
        public RenderPatch2() {}
        public static SpireReturn<Object> Prefix(AbstractPlayer the_AbstractPlayer,SpriteBatch sb) {
        if (AbstractDungeon.player instanceof Decade) {
            if (((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() instanceof MonsterRoom) && !the_AbstractPlayer.isDead)) {   
            	if(AbstractDungeon.player.hasPower("KamenRideHibikiPower")&&!AbstractDungeon.player.hasPower("HibikiKurenaiPower")){
            		SpecialHibikiTaiko.update();
            		SpecialHibikiTaiko.render(sb);
            	}
            	return SpireReturn.Continue();
            }
            else {
            	return SpireReturn.Continue();
            }
        }
		return SpireReturn.Continue();
        }
    }
    
	@SpirePatch(clz = AbstractPlayer.class, method = "combatUpdate")
	public static class PotionSaceUpdatePatch {
		@SpirePostfixPatch
		public static void Postfix(AbstractPlayer __instance) {
	        if (AbstractDungeon.player instanceof Decade) {
	            if (((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() instanceof MonsterRoom) && !AbstractDungeon.player.isDead))
	            { 
		        	if(AbstractDungeon.player.hasPower("KamenRideHibikiPower")&&!AbstractDungeon.player.hasPower("HibikiKurenaiPower")&&HibikiTaikoKeyEvent.TaikoTrigger){
		        		HibikiTaikoKeyEvent.update();
					}
	            }
	        }
		}
	}
    
}
