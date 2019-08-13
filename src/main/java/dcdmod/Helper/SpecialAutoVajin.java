package dcdmod.Helper;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcdmod.Actions.TurnTimer;
import dcdmod.Patches.AbstractSummonedAnimation;

public class SpecialAutoVajin
{

    public static int a;
    public static int cf;

    public SpecialAutoVajin() {
    }
    
   
    
    public static void update() {
    	if(!TurnTimer.BattleEnd) {
        	if (a == cf) {
                System.out.println("==a");
                return;
            }
        	cf = a;
            AbstractSummonedAnimation AutoVajin ;
            String AUTOVAJIN_ATLAS = "img/char/DCD_Animation/autovajin/autovajin.atlas";
            if (a == 1) {
                AbstractSummonedAnimation.clear("AutoVajin");
                String AUTOVAJIN_JSON1 = "img/char/DCD_Animation/autovajin/autovajin_appear.json";
                new AbstractSummonedAnimation("AutoVajin", AUTOVAJIN_ATLAS, AUTOVAJIN_JSON1, 0.8f, AbstractDungeon.player.drawX + 150.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                AutoVajin =  AbstractSummonedAnimation.getAnimation("AutoVajin");
                AutoVajin.setMovable(false);
            	AutoVajin.state.setAnimation(0, "appear", false);
            }
            else if (a == 2) {
                AbstractSummonedAnimation.clear("AutoVajin");
                String AUTOVAJIN_JSON2 = "img/char/DCD_Animation/autovajin/autovajin_attack.json";
                new AbstractSummonedAnimation("AutoVajin", AUTOVAJIN_ATLAS, AUTOVAJIN_JSON2, 0.8f, AbstractDungeon.player.drawX + 150.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                AutoVajin =  AbstractSummonedAnimation.getAnimation("AutoVajin");
                AutoVajin.setMovable(false);
            	AutoVajin.state.setAnimation(0, "attack", false);
            }
            else if (a == 3) {
                AbstractSummonedAnimation.clear("AutoVajin");
                String AUTOVAJIN_JSON3 = "img/char/DCD_Animation/autovajin/autovajin_disappear.json";
                new AbstractSummonedAnimation("AutoVajin", AUTOVAJIN_ATLAS, AUTOVAJIN_JSON3, 0.8f, AbstractDungeon.player.drawX + 150.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                AutoVajin =  AbstractSummonedAnimation.getAnimation("AutoVajin");
                AutoVajin.setMovable(false);
            	AutoVajin.state.setAnimation(0, "disappear", false);
            }
            else if (a == 4) {
                AbstractSummonedAnimation.clear("AutoVajin");
                String AUTOVAJIN_JSON4 = "img/char/DCD_Animation/autovajin/autovajin_defend.json";
                new AbstractSummonedAnimation("AutoVajin", AUTOVAJIN_ATLAS, AUTOVAJIN_JSON4, 0.8f, AbstractDungeon.player.drawX + 150.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                AutoVajin =  AbstractSummonedAnimation.getAnimation("AutoVajin");
                AutoVajin.setMovable(false);
            	AutoVajin.state.setAnimation(0, "defend", false);
            }
            else if(a == 5) {
                AbstractSummonedAnimation.clear("AutoVajin");
                String AUTOVAJIN_JSON5 = "img/char/DCD_Animation/autovajin/autovajin_normal.json";
                new AbstractSummonedAnimation("AutoVajin", AUTOVAJIN_ATLAS, AUTOVAJIN_JSON5, 0.8f, AbstractDungeon.player.drawX + 150.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                AutoVajin =  AbstractSummonedAnimation.getAnimation("AutoVajin");
                AutoVajin.setMovable(false);
            	AutoVajin.state.setAnimation(0, "normal", true);
            }
    	}
    }
    

    
    public static void render() {

    }
    
}
